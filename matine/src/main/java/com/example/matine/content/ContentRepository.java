package com.example.matine.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm içerikler içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    // İçeriği içeriğin adı ile arama
    Optional<Content> findContentByContentName(String contentName);

    // Aynı isimdeki içerikleirn listesi için içerik adı ile arama
    List<Content> searchContentByContentName(String contentName);

    // Belli bir türe ait bütün içerikleri o türün id bilgisi ile arama
    List<Content> findContentByGenreId(Long genreId);
}
