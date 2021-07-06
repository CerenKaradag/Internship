package com.example.matine.model;

import javax.persistence.*;

@Entity
@Table
public class Comment {

    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )

    private Long commentId;
    private Long contentId;
    private Long userId;
    private String commentBody;
    private Boolean isReported;

    public Comment() {
    }

    public Comment(Long commentId, Long contentId, Long userId, String commentBody, Boolean isReported) {
        this.commentId = commentId;
        this.contentId = contentId;
        this.userId = userId;
        this.commentBody = commentBody;
        this.isReported = isReported;
    }

    public Comment(Long contentId, Long userId, String commentBody, Boolean isReported) {
        this.contentId = contentId;
        this.userId = userId;
        this.commentBody = commentBody;
        this.isReported = isReported;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Boolean getReported() {
        return isReported;
    }

    public void setReported(Boolean reported) {
        isReported = reported;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", contentId=" + contentId +
                ", userId=" + userId +
                ", commentBody='" + commentBody + '\'' +
                ", isReported=" + isReported +
                '}';
    }
}
