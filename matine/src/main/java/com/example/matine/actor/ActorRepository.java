package com.example.matine.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm içerikler içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

    // Aktörün isim bilgisi ile bulunmaı
    Optional<Actor> findByActorName(String actorName);

    // Bir içeriğe ait aktörlerin içerik id bilgisi ile bulunması
    List<Actor> findByContentId(Long contentId);

    // Aktörün id bilgisi ile bulunması
    Optional<Actor> findByActorId(Long actorId);
}
