package com.example.matine.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm türlerin içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    // Bir türün tür ismi kullanılarak bulunması
    Optional<Genre> findGenreByName(String name);

}
