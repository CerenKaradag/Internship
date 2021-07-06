package com.example.matine.model;

import javax.persistence.*;

@Entity
@Table
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

    private String reportedUserName;
    private Long reportId;
    private Long reportingId;
    private Long reportedId;
    private String reportDescription;

    public ReportUser() {
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

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getReportingId() {
        return reportingId;
    }

    public void setReportingId(Long reportingId) {
        this.reportingId = reportingId;
    }

    public Long getReportedId() {
        return reportedId;
    }

    public void setReportedId(Long reportedId) {
        this.reportedId = reportedId;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getReportedUserName() {
        return reportedUserName;
    }

    public void setReportedUserName(String reportedUserName) {
        this.reportedUserName = reportedUserName;
    }

    @Override
    public String toString() {
        return "ReportUser{" +
                "reportedUserName='" + reportedUserName + '\'' +
                ", reportId=" + reportId +
                ", reportingId=" + reportingId +
                ", reportedId=" + reportedId +
                ", reportDescription='" + reportDescription + '\'' +
                '}';
    }
}
