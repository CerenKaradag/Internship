package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class ReportUser {

    @Id
    @SequenceGenerator(
            name = "reportUser_sequence",
            sequenceName = "reportUser_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reportUser_sequence"
    )

    private Long reportId;
    private Long reportingId;
    private Long reportedId;
    private String reportDescription;
    private String reportedUserName;

    public ReportUser() {
    }

    public ReportUser(String reportDescription, String reportedUserName) {
        this.reportDescription = reportDescription;
        this.reportedUserName = reportedUserName;
    }
    public ReportUser(Long reportId, Long reportingId, Long reportedId, String reportDescription, String reportedUserName) {
        this.reportId = reportId;
        this.reportingId = reportingId;
        this.reportedId = reportedId;
        this.reportDescription = reportDescription;
        this.reportedUserName = reportedUserName;
    }

    public ReportUser(Long reportingId, Long reportedId, String reportDescription, String reportedUserName) {
        this.reportingId = reportingId;
        this.reportedId = reportedId;
        this.reportDescription = reportDescription;
        this.reportedUserName = reportedUserName;
    }


}
