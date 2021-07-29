package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Rate {

    // Oy modeli için oluşturulmuş rate sınıfı
    // Bir oyun sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta oyların veri tabanına bağlantısı sağlanmaktadır.
    @Id
    @SequenceGenerator(
            name = "rate_sequence",
            sequenceName = "rate_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rate_sequence"
    )

    // Oyların değişkenleri

    private Long rateId;
    private Long contentId;
    private Long userId;
    private Integer rate;

    // Bu sınıfa ait constructorlar

    public Rate() {
    }

    public Rate(Long rateId, Long contentId, Long userId, Integer rate) {
        this.rateId = rateId;
        this.contentId = contentId;
        this.userId = userId;
        this.rate = rate;
    }

    public Rate(Long contentId, Long userId, Integer rate) {
        this.contentId = contentId;
        this.userId = userId;
        this.rate = rate;
    }


}
