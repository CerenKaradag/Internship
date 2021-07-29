package com.example.matine.content;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Content {

    // İçerik modeli için oluşturulmuş content sınıfı
    // Bir içeriğin sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta içeriklerin veri tabanına bağlantısı sağlanmaktadır.

    @Id
    @SequenceGenerator(
            name = "content_sequence",
            sequenceName = "content_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "content_sequence"
    )

    // İçeriklerin değişkenleri

    private Long contentId;
    private String contentName;
    private String contentDescription;
    private String genre;
    private ContentType contentType;
    private Long genreId;

    // Bu sınıfa ait constructorlar

    public Content() {
        super();
    }

    public Content(Long contentId,
                   String contentName,
                   String contentDescription,
                   String genre,
                   ContentType contentType, Long genreId) {
        super();
        this.contentId = contentId;
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.genre = genre;
        this.contentType = contentType;
        this.genreId = genreId;
    }

    public Content(String contentName,
                   String contentDescription,
                   String genre,
                   ContentType contentType,
                   Long genreId) {
        super();
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.genre = genre;
        this.contentType = contentType;
        this.genreId  = genreId;
    }


}
