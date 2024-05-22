package hello.newsService.service;

import hello.newsService.entity.News;

import java.util.List;

public interface NewsService {
    News addNews(News news);
    List<News> getAllNews();
    News getNewsById(int id);
    void deleteNews(int id);
}
