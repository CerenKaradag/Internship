package com.example.matine.repository;

import com.example.matine.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Veri tabanına kayıtlı olan tüm arşivlerin içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf

public interface ArchiveRepository extends JpaRepository<Archive, Long> {

    // Bir kullanıcıya ait bütün arşivlemelere kullanıcının id bilgisinin kullanılmasıyla erişilmesi
    List<Archive> findArchiveByUserId(Long userId);

    // Bir arşivin kullanıcı id bilgisi ve içerik id bilgisi kullanılarak bulunması
    Archive findArchiveByUserIdAndContentId(Long userId, Long contentId);


}
