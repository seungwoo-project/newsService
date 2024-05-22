package hello.newsService.controller;

import hello.newsService.entity.News;
import hello.newsService.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public String 뉴스목록2(Model model) {

        List<News> news = newsService.getAllNews();
        model.addAttribute("newslist", news);
        return "basic/newsList";
    }
    @GetMapping("/{newsId}")
    public String 뉴스상세(@PathVariable int newsId, Model model) {
        News newsById = newsService.getNewsById(newsId);
        model.addAttribute("news", newsById);
        return "basic/newsView";
    }

    @GetMapping("/del")
    @ResponseBody
    public String 뉴스삭제() {

        return "ok";
    }


}
