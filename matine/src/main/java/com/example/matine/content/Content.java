package com.example.matine.content;

import javax.persistence.*;

@Entity
@Table
public class Content {

    @Id
    @SequenceGenerator(
            name = "content_sequence",
            sequenceName = "content_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "content_sequence"
    )

    private Long contentId;
    private String contentName;
    private String contentDescription;
    private String genre;
    private ContentType contentType;
    private Long genreId;

    public Content() {
        super();
    }

    public Content(Long contentId,
                   String contentName,
                   String contentDescription,
                   String genre,
                   ContentType contentType, Long genreId) {
        super();
        this.contentId = contentId;
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.genre = genre;
        this.contentType = contentType;
        this.genreId = genreId;
    }

    public Content(String contentName,
                   String contentDescription,
                   String genre,
                   ContentType contentType,
                   Long genreId) {
        super();
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.genre = genre;
        this.contentType = contentType;
        this.genreId  = genreId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Long getGenreId() { return genreId; }

    public void setGenreId(Long genreId) { this.genreId = genreId; }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", contentName='" + contentName + '\'' +
                ", contentDescription='" + contentDescription + '\'' +
                ", genre='" + genre + '\'' +
                ", contentType=" + contentType + '\'' +
                ", genreId=" + genreId +
                '}';
    }
}
