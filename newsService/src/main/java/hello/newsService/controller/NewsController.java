package hello.newsService.controller;

import hello.newsService.entity.News;
import hello.newsService.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @GetMapping("/")
    public String 뉴스목록(Model model) {

        List<News> news = newsService.getAllNews();
        model.addAttribute("newslist", news);
        return "basic/newsList";
    }

}
