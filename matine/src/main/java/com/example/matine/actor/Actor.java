package com.example.matine.actor;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
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


}
