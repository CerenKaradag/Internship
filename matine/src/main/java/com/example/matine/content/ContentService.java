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

@Service
public class ContentService {

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



    public void addNewContent(Content content) {

        System.out.println(content);
        Optional<Content> contentOptional = contentRepository.findContentByContentName(content.getContentName());
        if(contentOptional.isPresent()){
            throw new ApiRequestException("This content already exists!");
        }
        Optional<Genre> genre = genreRepository.findGenreByName(content.getGenre());
        content.setGenreId(genre.get().getId());
        contentRepository.save(content);

    }




    public void reportComment(Long contentId,ReportComment reportComment) {

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

    public List<ReportComment> getReportedComments(Long contentId) {
        ArrayList<ReportComment> reportComment = new ArrayList<>();
        
        for (int j = 0; j < reportCommentRepository.findAll().size() ; j++){
            if(reportCommentRepository.findAll().get(j).getContentId() == contentId){
                reportComment.add(reportCommentRepository.findAll().get(j));
            }
        }
        
        return reportComment;
    }

    public void deleteReportedComments(Long commentId) {
        reportCommentRepository.delete(reportCommentRepository.findByCommentId(commentId).get());
        commentRepository.deleteById(commentId);
    }

    public void addContentToMyArchive(Long userId, Content content) {

        Archive existedArchive = archiveRepository.findArchiveByUserIdAndContentId(userId,content.getContentId() );
        Archive myArchive = new Archive(userId,content.getContentId());
        if( existedArchive.getContentId() == myArchive.getContentId() ){
            throw new ApiRequestException("Bu içerik arşivinize ekli!");
        }
        archiveRepository.save(myArchive);
    }

    public List<Content> searchContents(Content content) {
        return contentRepository.searchContentByContentName(content.getContentName());
    }

    public List<Content> getContentWithGenreId(Long genreId) {
            List<Content> contents = contentRepository.findContentByGenreId(genreId);
            return contents;

    }

    public List<Content> getContents() {
        return contentRepository.findAll();
    }

    public List<ReportComment> getAllReportedComments() {
        return reportCommentRepository.findAll();
    }

    public void deleteContentFromMyArchive(Long userId, Archive archive) {

        Archive foundArchive = archiveRepository.findArchiveByUserIdAndContentId(userId, archive.getContentId());
        archiveRepository.deleteById(foundArchive.getArchiveId());
    }
}
