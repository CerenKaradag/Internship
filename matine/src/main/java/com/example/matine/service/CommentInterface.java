package com.example.matine.service;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.Comment;
import java.util.List;

public interface CommentInterface {

    List<Comment> getComments (Long contentId);

    void addComment(Long contentId, Long userId, Comment comment);

    void deleteComment(Long commentId) throws ApiRequestException;
}
