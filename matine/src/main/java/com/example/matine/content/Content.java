package com.example.matine.content;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
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


}
