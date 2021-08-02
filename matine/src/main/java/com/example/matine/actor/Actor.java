package com.example.matine.actor;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Actor {

    // Oyuncu modeli için oluşturulmuş actor sınıfı
    // Bir aktörün sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta aktörlerin veri tabanına bağlantısı sağlanmaktadır.

    @Id
    @SequenceGenerator(
            name = "actor_sequence",
            sequenceName = "actor_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actor_sequence"
    )

    // Aktörlerin değişkenleri
    private Long actorId;
    private Long contentId;
    private String actorName;
    private String actorRole;

    // Bu sınıfa ait constructorlar

    public Actor() {
    }

    public Actor(Long contentId, Long actorId, String actorName, String actorRole) {
        this.contentId = contentId;
        this.actorId = actorId;
        this.actorName = actorName;
        this.actorRole = actorRole;
    }

    public Actor(Long contentId, String actorName, String actorRole) {
        this.contentId = contentId;
        this.actorName = actorName;
        this.actorRole = actorRole;
    }


}
