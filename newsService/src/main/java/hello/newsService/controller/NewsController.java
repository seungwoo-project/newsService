package hello.newsService.controller;

import hello.newsService.entity.News;
import hello.newsService.service.NewsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/{newsId}/del")
    public String 뉴스삭제(@PathVariable int newsId) {
        newsService.deleteNews(newsId);
        return "redirect:/";
    }

    //  뉴스 등록, newsList 새로고침 구현
    @PostMapping("/add")
    public String addNews(@ModelAttribute News news, @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        try {
            byte[] imageBytes = imageFile.getBytes();
            news.setImage(imageBytes);
            newsService.addNews(news);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "basic/newsList";
        }
        return "redirect:/";
    }

    // 이미지 처리
    @GetMapping("/images/{newsId}")
    public void showImage(@PathVariable int newsId, HttpServletResponse response) throws IOException {
        byte[] imageBytes = newsService.getImageById(newsId);
        response.setContentType("image/jpeg");
        response.getOutputStream().write(imageBytes);
    }
}
