package com.example.matine.repository;

import com.example.matine.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment> findByCommentId(Long commentId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByContentId(Long contentId);
}
