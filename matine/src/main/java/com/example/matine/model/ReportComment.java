package com.example.matine.model;

import javax.persistence.*;

@Entity
@Table
public class ReportComment {

    @Id
    @SequenceGenerator(
            name = "reportComment_sequence",
            sequenceName = "reportComment_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reportComment_sequence"
    )

    private Long commentReportId;
    private Long reportingId;
    private Long reportedId;
    private Long commentId;
    private Long contentId;
    private String commentDescription;

    public ReportComment() {
    }

    public ReportComment(Long commentReportId, Long reportingId, Long reportedId, Long commentId, Long contentId, String commentDescription) {
        this.commentReportId = commentReportId;
        this.reportingId = reportingId;
        this.reportedId = reportedId;
        this.commentId = commentId;
        this.contentId = contentId;
        this.commentDescription = commentDescription;
    }

    public ReportComment(Long reportingId, Long reportedId, Long commentId, Long contentId, String commentDescription) {
        this.reportingId = reportingId;
        this.reportedId = reportedId;
        this.commentId = commentId;
        this.contentId = contentId;
        this.commentDescription = commentDescription;
    }

    public Long getCommentReportId() {
        return commentReportId;
    }

    public void setCommentReportId(Long commentReportId) {
        this.commentReportId = commentReportId;
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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    @Override
    public String toString() {
        return "ReportComment{" +
                "commentReportId=" + commentReportId +
                ", reportingId=" + reportingId +
                ", reportedId=" + reportedId +
                ", commentId=" + commentId +
                ", contentId=" + contentId +
                ", commentDescription='" + commentDescription + '\'' +
                '}';
    }
}
