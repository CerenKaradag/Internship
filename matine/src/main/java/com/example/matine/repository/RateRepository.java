package com.example.matine.repository;

import com.example.matine.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm oylar içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    // Oy değeri ile oyun bulunması
    Optional<Rate> findByRate(Integer rate);

    // Kullanıcı id bilgisi ile oyun bulunması
    Optional<Rate> findByUserId(Long userId);

    // Oy id bilgisi ile oyun bulunması
    List<Rate> findByRateId(Long rateId);

    // İçerik id bilgisi ile oyun bulunması
    List<Rate> findByContentId(Long contentId);
}
