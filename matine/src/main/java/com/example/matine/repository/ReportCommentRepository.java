package com.example.matine.repository;

import com.example.matine.model.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Veri tabanına kayıtlı olan tüm bildirili yorumlar içinden değişkenler ile spesifik veri
// ya da veri grubuna erişilmesi için gerekli fonskiyonların tanımlandığı sınıf

@Repository
public interface ReportCommentRepository extends JpaRepository<ReportComment, Long> {

    // Bildiri id bilgisi ile bildirili yorumun bulunması
    Optional<ReportComment> findByCommentReportId(Long commentReportId);

    // Bildiren id bilgisi ile bildirili yorumun bulunması
    Optional<ReportComment> findByReportingId(Long reportingId);

    // Bildirilen id bilgisi ile bildirili yorumun bulunması
    Optional<ReportComment> findByReportedId(Long reportedId);

    // Yorum id bilgisi ile bildirili yorumun bulunması
    Optional<ReportComment> findByCommentId(Long commentId);

    // İçerik id bilgisi ile bildirili yorumun bulunması
    Optional<ReportComment> findByContentId(Long contentId);

    // Yorum içeriği ile bildirili yorumun bulunması
    Optional<ReportComment> findByCommentDescription(String commentDescription);
}
