package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
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
    private String commentedUserName;
    private Boolean isReported;

    public Comment() {
    }

    public Comment(Long commentId, Long contentId, Long userId, String commentBody,String commentedUserName, Boolean isReported) {
        this.commentId = commentId;
        this.contentId = contentId;
        this.userId = userId;
        this.commentBody = commentBody;
        this.commentedUserName = commentedUserName;
        this.isReported = isReported;
    }

    public Comment(Long contentId, Long userId, String commentBody,String commentedUserName, Boolean isReported) {
        this.contentId = contentId;
        this.userId = userId;
        this.commentBody = commentBody;
        this.commentedUserName = commentedUserName;
        this.isReported = isReported;
    }


}
