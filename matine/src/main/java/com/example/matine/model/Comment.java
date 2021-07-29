package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Comment {

    // Yorum modeli için oluşturulmuş comment sınıfı
    // Bir yorumun sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta yorumların veri tabanına bağlantısı sağlanmaktadır.

    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )

    // Yorumların değişkenleri

    private Long commentId;
    private Long contentId;
    private Long userId;
    private String commentBody;
    private String commentedUserName;
    private Boolean isReported;

    // Bu sınıfa ait constructorlar

    public Comment() {
    }

    public Comment(Long commentId, Long contentId, Long userId, String commentBody,String commentedUserName, Boolean isReported) {
        this.commentId = commentId;
        this.contentId = contentId;
        this.userId = userId;
        this.commentBody = commentBody;
        this.commentedUserName = commentedUserName;
        this.isReported = isReported;
    }

    public Comment(Long contentId, Long userId, String commentBody,String commentedUserName, Boolean isReported) {
        this.contentId = contentId;
        this.userId = userId;
        this.commentBody = commentBody;
        this.commentedUserName = commentedUserName;
        this.isReported = isReported;
    }


}
