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

// Controller sınıfında tanımlanan fonksiyonların içerikleri ve çalışmasını sağlayan servis sınıfı

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    // Sistemde kayıtlı olan bütün yorumlara erişilmesini sağlayan fonksiyon
    public List<Comment> getComments(Long contentId) {
        return commentRepository.findByContentId(contentId);
    }


    // Kullanıcının istediği içeriğe yorum eklemesini sağlayan fonksiyondur
    @Transactional
    public void addComment(Long contentId, Long userId, Comment comment) {

        User user = userRepository.getById(userId);
        comment.setUserId(userId);
        comment.setContentId(contentId);
        comment.setCommentedUserName(user.getUserName());
        commentRepository.save(comment);
    }

    // Kullanıcının veya sistem yöneticisinin silme işlemi yaptığı zaman çalışan fonksiyondur
    public void deleteComment(Long commentId) {

        // Sisteme silinmesi için gönderilen bilgiler dahilinde ilgili yorumun sistemde kayıtlı olup olmadığı
        // kontrol edilir, eğer kayıtlı değilse ilgili hata önyüze iletilir, kayıtlı ise yorum silinir
        Optional<Comment> commentOptional = commentRepository.findByCommentId(commentId);
        if (!commentOptional.isPresent()) {
            throw new ApiRequestException("Böyle bir yorum bulunmamakta!");
        }

        commentRepository.deleteById(commentId);
    }
}
