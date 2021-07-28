package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Rate {

    @Id
    @SequenceGenerator(
            name = "rate_sequence",
            sequenceName = "rate_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rate_sequence"
    )

    private Long rateId;
    private Long contentId;
    private Long userId;
    private Integer rate;

    public Rate() {
    }

    public Rate(Long rateId, Long contentId, Long userId, Integer rate) {
        this.rateId = rateId;
        this.contentId = contentId;
        this.userId = userId;
        this.rate = rate;
    }

    public Rate(Long contentId, Long userId, Integer rate) {
        this.contentId = contentId;
        this.userId = userId;
        this.rate = rate;
    }


}
