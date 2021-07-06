package com.example.matine.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table
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

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", contentId=" + contentId +
                ", userId=" + userId +
                ", rate=" + rate +
                '}';
    }
}
