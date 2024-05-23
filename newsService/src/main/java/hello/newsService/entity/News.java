package hello.newsService.entity;


import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;



@Getter @Setter
public class News {

    private int id;
    private String title;

    private byte[] image;

    private String date;

    private String content;

}
