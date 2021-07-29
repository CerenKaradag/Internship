package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class ReportUser {

    // Bildirili üye modeli için oluşturulmuş sınıf
    // Bir bildirili üyenin sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta bildirili üyelerin veri tabanına bağlantısı sağlanmaktadır.
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

    // Bildirili üyelerin değişkenleri

    private Long reportId;
    private Long reportingId;
    private Long reportedId;
    private String reportDescription;
    private String reportedUserName;

    // Bu sınıfa ait constructorlar

    public ReportUser() {
    }

    public ReportUser(String reportDescription, String reportedUserName) {
        this.reportDescription = reportDescription;
        this.reportedUserName = reportedUserName;
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


}
