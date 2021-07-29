package com.example.matine.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class ReportComment {

    // Bildirili yorum modeli için oluşturulmuş sınıf
    // Bir bildirili yorumun sahip olduğu bütün değişklenler ve oluşturulun constructorlar bu sınıfta bulunmaktadır.
    // Lombok kullanılarak Getter-Setter fonksiyonları ve ToString fonksiyonu yazılmasına gerek kalmamıştır.
    // @Data componenti ile bu fonksiyonlar oluşturulmuş ve erişilebilirdir.
    // Ayrıca bu sınıfta bildirili yorumların veri tabanına bağlantısı sağlanmaktadır.

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

    // Bildirili yorumların değişkenleri

    private Long commentReportId;
    private Long reportingId;
    private Long reportedId;
    private Long commentId;
    private Long contentId;
    private String commentDescription;

    // Bu sınıfa ait constructorlar

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


}
