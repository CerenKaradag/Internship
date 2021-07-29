package com.example.matine.content;

import com.example.matine.model.Archive;
import com.example.matine.model.ReportComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContentController {

    // HTTP requestlerinin alındığı ve servis sınıfı
    // ile ilgili fonksiyonların çalışması için bağlantıya sahip controller sınıfı

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {

        this.contentService = contentService;
    }

    // Veri tabanına kayıtlı bütün içeriklere ulaşılması
    @GetMapping(path="matine/contents")
    public List<Content> getAllContents(){
        return  contentService.getContents();
    }

    // İlgili türe ait içeriklere ulaşılması
    @GetMapping(path="matine/{genreID}/contents")
    public List<Content> getContentWithGenreId(@PathVariable("genreID") Long genreId){
        return  contentService.getContentWithGenreId(genreId);
    }

    // Herhangi bir türe ait yeni içeriğin eklenmesi
    @PostMapping(path = "/matine/{genreName}/content")
    public void createNewContent(@RequestBody Content content){
        contentService.addNewContent(content);
    }

    // YORUM BİLDİRİLERİ

    // Herhangi bir içeriğe ait bildirili yorumlara erişilmesi
    @GetMapping(path = "/matine/report/{contentId}" )
    public List<ReportComment> getReportedComments(@PathVariable ("contentId") Long contentId) {
        return contentService.getReportedComments(contentId);
    }

    // Sistemde kayıtlı olan bütün bildirili yorumlara ulaşılması
    @GetMapping(path = "/matine/report/comments" )
    public List<ReportComment> getAllReportedComments() {
        return contentService.getAllReportedComments();
    }

    // Herhangi bi yorumun bildirilmesi
    @PostMapping(path = "/matine/report/{contentId}" )
    public void reportComment(@PathVariable ("contentId") Long contentId,
                              @RequestBody ReportComment reportComment) {
        contentService.reportComment(contentId,reportComment);
    }

    // Sistem yöneticisi tarafından bildirilen herhangi bir yorumun silinmesi
    @DeleteMapping(path = "/matine/report/{commentId}" )
    public void deleteReportedComments(@PathVariable ("commentId") Long commentId) {
        contentService.deleteReportedComments(commentId);
    }

    // ARŞİV OPERASYONLARI

    // Herhangi bir içeriğin kullanıcının arşivine eklenmesi
    @PostMapping(path = "/matine/profile/archive/{userId}")
    public void addContentToMyArchive(@PathVariable ("userId") Long userId,
                                      @RequestBody Content content) {
        contentService.addContentToMyArchive(userId,content);
    }

    // Kullanıcının dilediği içeriği arşivinden çıkarması
    @DeleteMapping(path = "/matine/profile/archive/{userId}")
    public void removeContentFromMyArchive(@PathVariable ("userId") Long userId,
                                           @RequestBody Archive archive) {
        contentService.deleteContentFromMyArchive(userId,archive);
    }

    // Sistemde kayıtlı olan içerikler içinden arama yapılıp istenilen içeriğe erişilmesi
    @PostMapping(path = "/matine/search")
    public List<Content> search(@RequestBody Content content){
        return contentService.searchContents(content);
    }




}
