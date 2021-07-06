package com.example.matine.model;

import javax.persistence.*;

@Entity
@Table
public class Archive {

    @Id
    @SequenceGenerator(
            name = "archive_sequence",
            sequenceName = "archive_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "archive_sequence"
    )

    private Long archiveId;
    private Long userId;
    private Long contentId;

    public Archive() {
    }

    public Archive(Long archiveId, Long userId, Long contentId) {
        this.archiveId = archiveId;
        this.userId = userId;
        this.contentId = contentId;
    }

    public Archive(Long userId, Long contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }

    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "archiveId=" + archiveId +
                ", userId=" + userId +
                ", contentId=" + contentId +
                '}';
    }
}
