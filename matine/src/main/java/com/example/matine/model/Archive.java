package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Archive {

    // Arşiv modeli için oluşturulmuş archive sınıfı
    // Bir arşivin sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta arşivlerin veri tabanına bağlantısı sağlanmaktadır.

    @Id
    @SequenceGenerator(
            name = "archive_sequence",
            sequenceName = "archive_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "archive_sequence"
    )

    // Arşiv değişkenleri

    private Long archiveId;
    private Long userId;
    private Long contentId;

    // Bu sınıfa ait constructorlar

    public Archive() {
    }

    public Archive(Long archiveId, Long userId, Long contentId) {
        this.archiveId = archiveId;
        this.userId = userId;
        this.contentId = contentId;
    }

    public Archive(Long userId, Long contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }

}
