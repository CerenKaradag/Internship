package com.example.matine.repository;

import com.example.matine.model.ReportUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportUserRepository extends JpaRepository<ReportUser,Long> {

    Optional<ReportUser> findReportUserByReportedIdAndReportingId(Long reportedId, Long reportingId);

    List<ReportUser> findReportUserByReportId(Long reportId);


}
