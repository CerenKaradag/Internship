package com.example.matine.genre;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Genre {

    // Tür modeli için oluşturulmuş genre sınıfı
    // Bir türün sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta türlerin veri tabanına bağlantısı sağlanmaktadır.
    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence"
    )

    // Türlerin değişkenleri

    private Long id;
    private String name;

    // Bu sınıfa ait constructorlar

    public Genre() {
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }


}
