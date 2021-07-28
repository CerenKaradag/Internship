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

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {

        this.contentService = contentService;
    }

    @GetMapping(path="matine/contents")
    public List<Content> getAllContents(){
        return  contentService.getContents();
    }

    @GetMapping(path="matine/{genreID}/contents")
    public List<Content> getContentWithGenreId(@PathVariable("genreID") Long genreId){
        return  contentService.getContentWithGenreId(genreId);
    }

    @PostMapping(path = "/matine/{genreName}/content")
    public void createNewContent(@RequestBody Content content){
        contentService.addNewContent(content);
    }

    //yorum bildirme

    @GetMapping(path = "/matine/report/{contentId}" )
    public List<ReportComment> getReportedComments(@PathVariable ("contentId") Long contentId) {
        return contentService.getReportedComments(contentId);
    }

    @GetMapping(path = "/matine/report/comments" )
    public List<ReportComment> getAllReportedComments() {
        return contentService.getAllReportedComments();
    }

    @PostMapping(path = "/matine/report/{contentId}" )
    public void reportComment(@PathVariable ("contentId") Long contentId,
                              @RequestBody ReportComment reportComment) {
        contentService.reportComment(contentId,reportComment);
    }

    @DeleteMapping(path = "/matine/report/{commentId}" )
    public void deleteReportedComments(@PathVariable ("commentId") Long commentId) {
        contentService.deleteReportedComments(commentId);
    }



    @PostMapping(path = "/matine/profile/archive/{userId}")
    public void addContentToMyArchive(@PathVariable ("userId") Long userId,
                                      @RequestBody Content content) {
        contentService.addContentToMyArchive(userId,content);
    }

    @DeleteMapping(path = "/matine/profile/archive/{userId}")
    public void removeContentFromMyArchive(@PathVariable ("userId") Long userId,
                                           @RequestBody Archive archive) {
        contentService.deleteContentFromMyArchive(userId,archive);
    }

    @PostMapping(path = "/matine/search")
    public List<Content> search(@RequestBody Content content){
        return contentService.searchContents(content);
    }




}
