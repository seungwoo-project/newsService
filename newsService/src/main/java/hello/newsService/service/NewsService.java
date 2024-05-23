// NewsService 인터페이스
package hello.newsService.service;

import hello.newsService.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(int id);
    void addNews(News news);
    void deleteNews(int id);
    byte[] getImageById(int id);
}