package com.example.matine.service;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.Comment;
import com.example.matine.repository.CommentRepository;
import com.example.matine.user.User;
import com.example.matine.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getComments(Long contentId) {
        return commentRepository.findByContentId(contentId);
    }


    @Transactional
    public void addComment(Long contentId, Long userId, Comment comment) {

        User user = userRepository.getById(userId);

        comment.setUserId(userId);
        comment.setContentId(contentId);
        comment.setCommentedUserName(user.getUserName());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findByCommentId(commentId);
        if (!commentOptional.isPresent()) {
            throw new ApiRequestException("Comment does not exist.");
        }

        System.out.println(commentId);
        commentRepository.deleteById(commentId);
    }
}
