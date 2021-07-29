package com.example.matine.repository;

import com.example.matine.model.ReportUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm bildirili kullanıcılar içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf

public interface ReportUserRepository extends JpaRepository<ReportUser,Long> {

    // Bildirili kullanıcının bildiren ve bildirilen id bilgileri ile bulunması
    Optional<ReportUser> findReportUserByReportedIdAndReportingId(Long reportedId, Long reportingId);

    // Bildiri id bilgisi ile bildirili kullanıcının bulunması
    List<ReportUser> findReportUserByReportId(Long reportId);


}
