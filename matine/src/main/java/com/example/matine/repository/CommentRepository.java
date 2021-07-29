package com.example.matine.repository;

import com.example.matine.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm yorumlar içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf

public interface CommentRepository extends JpaRepository<Comment,Long> {

    // Yorum id bilgisi ile yorumun bulunması
    Optional<Comment> findByCommentId(Long commentId);

    // Bir kullanıcıya ait bütün yorumların kullanıcı id bilgisi ile bulunması
    List<Comment> findByUserId(Long userId);

    // Bir içeriğe ait bütün yorumların içerik id bilgisi ile bulunması
    List<Comment> findByContentId(Long contentId);
}
