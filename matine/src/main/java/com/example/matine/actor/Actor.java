package com.example.matine.actor;

import javax.persistence.*;

@Entity
@Table
public class Actor {

    @Id
    @SequenceGenerator(
            name = "actor_sequence",
            sequenceName = "actor_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actor_sequence"
    )

    private Long contentId;
    private Long actorId;
    private String actorName;
    private String actorRole;

    public Actor() {
    }

    public Actor(Long contentId, Long actorId, String actorName, String actorRole) {
        this.contentId = contentId;
        this.actorId = actorId;
        this.actorName = actorName;
        this.actorRole = actorRole;
    }

    public Actor(Long contentId, String actorName, String actorRole) {
        this.contentId = contentId;
        this.actorName = actorName;
        this.actorRole = actorRole;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorRole() {
        return actorRole;
    }

    public void setActorRole(String actorRole) {
        this.actorRole = actorRole;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "contentId=" + contentId +
                ", actorId=" + actorId +
                ", actorName='" + actorName + '\'' +
                ", actorRole='" + actorRole + '\'' +
                '}';
    }
}
