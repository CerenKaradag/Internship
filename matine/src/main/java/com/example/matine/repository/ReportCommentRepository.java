package com.example.matine.repository;

import com.example.matine.model.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ReportCommentRepository extends JpaRepository<ReportComment, Long> {

    Optional<ReportComment> findByCommentReportId(Long commentReportId);

    Optional<ReportComment> findByReportingId(Long reportingId);

    Optional<ReportComment> findByReportedId(Long reportedId);

    Optional<ReportComment> findByCommentId(Long commentId);

    Optional<ReportComment> findByContentId(Long contentId);

    Optional<ReportComment> findByCommentDescription(String commentDescription);
}
