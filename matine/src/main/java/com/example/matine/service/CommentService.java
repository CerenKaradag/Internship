package com.example.matine.service;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.Comment;
import com.example.matine.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(Long contentId) {
        return commentRepository.findByContentId(contentId);
    }


    @Transactional
    public void addComment(Long contentId, Long userId, Comment comment) {

        comment.setUserId(userId);
        comment.setContentId(contentId);
        commentRepository.save(comment);
    }

    public void deleteComment(Long userId, Comment comment) {
        Optional<Comment> commentOptional = commentRepository.findByCommentId(comment.getCommentId());
        if (!commentOptional.isPresent()) {
            throw new ApiRequestException("Comment does not exist.");
        }
        commentRepository.deleteById(commentOptional.get().getCommentId());
    }
}
