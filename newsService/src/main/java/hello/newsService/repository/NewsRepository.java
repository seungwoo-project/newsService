package hello.newsService.repository;

import hello.newsService.entity.News;

import java.util.List;

public interface NewsRepository {
    void addNews(News news);
    List<News> getAll();
    News getNews(int id);
    void delNews(int id);
    byte[] getImageById(int id);
}