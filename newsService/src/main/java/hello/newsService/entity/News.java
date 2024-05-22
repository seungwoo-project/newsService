package hello.newsService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDate;


@Getter @Setter
public class News {

    private int id;
    private String title;

    @Lob
    private Blob image;

    private String date;

    private String content;

}
