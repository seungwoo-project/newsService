package hello.newsService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @GetMapping
    public String 뉴스목록() {

        return "newsList";
    }

}
