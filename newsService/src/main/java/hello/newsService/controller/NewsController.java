package hello.newsService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController {

    @GetMapping("/news")
    @ResponseBody
    public String 뉴스목록() {

        return "ok";
    }

}
