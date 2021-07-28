package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
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

}
