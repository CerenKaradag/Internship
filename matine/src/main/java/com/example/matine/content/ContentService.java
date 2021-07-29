package com.example.matine.content;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.genre.Genre;
import com.example.matine.genre.GenreRepository;
import com.example.matine.model.Archive;
import com.example.matine.model.Comment;
import com.example.matine.model.ReportComment;
import com.example.matine.repository.ArchiveRepository;
import com.example.matine.repository.CommentRepository;
import com.example.matine.repository.ReportCommentRepository;
import com.example.matine.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// Controller sınıfında tanımlanan fonksiyonların içerikleri ve çalışmasını sağlayan servis sınıfı
@Service
public class ContentService {


    // Erişilmesi gerekebilecek olan repositorylerin erişimi

    public final ContentRepository contentRepository;
    public final UserRepository userRepository;
    public final GenreRepository genreRepository;
    public final ReportCommentRepository reportCommentRepository;
    public final CommentRepository commentRepository;
    private final ArchiveRepository archiveRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository, UserRepository userRepository, GenreRepository genreRepository, ReportCommentRepository reportCommentRepository, CommentRepository commentRepository, ArchiveRepository archiveRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.reportCommentRepository = reportCommentRepository;
        this.commentRepository = commentRepository;
        this.archiveRepository = archiveRepository;
    }

    // Sisteme gönderilen içerik objesinin kullanılmasıyla veri
    // tabanına yeni bir içerik eklenmesini sağlayan fonksiyon
    public void addNewContent(Content content) {

        // Sisteme gönderilen içeriğin hali hazırda sistemde bulunup bulunmadığını kontrol eder
        // eğer sistemde bulunmaktaysa ilgili hatayı önyüze iletir, bulunmuyorsa içerik sisteme eklenir
        Optional<Content> contentOptional = contentRepository.findContentByContentName(content.getContentName());
        if(contentOptional.isPresent()){
            throw new ApiRequestException("Bu içerik daha önce eklendi!");
        }
        Optional<Genre> genre = genreRepository.findGenreByName(content.getGenre());
        content.setGenreId(genre.get().getId());
        contentRepository.save(content);

    }

    // Herhangi bir yorumun bildirilmesini sağlayan fonksiyon
    public void reportComment(Long contentId,ReportComment reportComment) {

        // Sisteme gönderilen yorumun ait olduğu içeriğin veri tabanında bulunup bulunmadığı kontrol edilir
        // eğer böyle bir içerik yoksa sistem ilgili hatayı önyüze iletir, varsa ilgili yorum bildirili
        // yorum olarak işaretlenir ve bildirili yorumlara kaydedilir

        Comment comment = commentRepository.findByCommentId(reportComment.getCommentId()).get();
        Optional<Content> content = contentRepository.findById(contentId);

        if (content.isEmpty()){
            throw new ApiRequestException("Böyle bir içerik yok!");
        }

        comment.setIsReported(true);
        reportComment.setContentId(contentId);
        reportComment.setCommentId(reportComment.getCommentId());
        reportComment.setCommentDescription(comment.getCommentBody());
        reportCommentRepository.save(reportComment);

    }

    // Herhangi bir içeriğe ait bütün bildirili yorumlara erişilmesini sağlayan fonksiyondur
    public List<ReportComment> getReportedComments(Long contentId) {

        // Veri tabanından alınacak bildirili yorumları tutmak için bir liste oluşturulur, içerik
        // id bilgisi sisteme gönderilen içeriğin id bilgisi ile eşleşenler bu listenin içinde eklenir
        // ve yanıt olarak bu liste döndürülür

        ArrayList<ReportComment> reportComment = new ArrayList<>();
        
        for (int j = 0; j < reportCommentRepository.findAll().size() ; j++){
            if(reportCommentRepository.findAll().get(j).getContentId() == contentId){
                reportComment.add(reportCommentRepository.findAll().get(j));
            }
        }
        
        return reportComment;
    }


    // Sistem yöneticisinin bildirili yorumları silmesini sağlayan fonksiyondur
    public void deleteReportedComments(Long commentId) {

        // Sisteme gönderilen yorum id bilgisi kullanılarak veri tabanında
        // kayıtlı olan yoruma erişilerek yorum sistemden ve veri tabanından silinir
        reportCommentRepository.delete(reportCommentRepository.findByCommentId(commentId).get());
        commentRepository.deleteById(commentId);
    }

    // Kullanıcının istediği içeriği arşivine eklemsini sağlayan fonksiyondur
    public void addContentToMyArchive(Long userId, Content content) {

        // Kullanıcının eklemek istediği içeriğin arşivinde bulunup bulunmadığı kontrol edilir,
        // eğer bulunuyorsa ilgili hata önyüze iletilir, bulunmuyorsa içeik kullanıcının arşivine eklenir

        Archive existedArchive = archiveRepository.findArchiveByUserIdAndContentId(userId,content.getContentId() );
        Archive myArchive = new Archive(userId,content.getContentId());

        if( existedArchive!= null && existedArchive.getContentId() == myArchive.getContentId() ){
            throw new ApiRequestException("Bu içerik arşivinize ekli!");
        }
        archiveRepository.save(myArchive);
    }

    // Kullanıcının arşivindeki içerikleri arşivinden çıkarmasını sağlayan fonksiyondur
    public void deleteContentFromMyArchive(Long userId, Archive archive) {

        // Kullanıcının id bilgisi ve gönderilen öğenin içerik id bilgisi kullanılarak
        // veri tabanında kayıtlı bulunan arşiv öğesine erişilir ve silinir

        Archive foundArchive = archiveRepository.findArchiveByUserIdAndContentId(userId, archive.getContentId());
        archiveRepository.deleteById(foundArchive.getArchiveId());
    }

    // Sistemde bulununan içeriklerin içinden kullanıcının istediği içeriği aramasını sağlayan fonskiyondur
    // Sisteme gönderilen içerik objesi kullanılarak veri tabanında arama yapılır ve bulunan içerik önyüze
    // yanıt olarak döndürülür
    public List<Content> searchContents(Content content) {
        return contentRepository.searchContentByContentName(content.getContentName());
    }

    // İçeriğin tür id bilgisi kullanılarak erişildiği fonksiyondur,
    // bu fonksiyon ile bir türe ait bütün içerikler bulunup döndürülür
    public List<Content> getContentWithGenreId(Long genreId) {

            List<Content> contents = contentRepository.findContentByGenreId(genreId);
            return contents;
    }

    // Sisteme kayıtlı bütün içeriklerin bulunduğu fonksiyondur
    public List<Content> getContents() {
        return contentRepository.findAll();
    }

    // Veri tabanında kayıtlı olan bütün bildirili yorumlara erişildiği fonksiyondur
    public List<ReportComment> getAllReportedComments() {
        return reportCommentRepository.findAll();
    }


}
