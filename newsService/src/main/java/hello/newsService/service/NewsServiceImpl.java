// NewsServiceImpl 구현체
package hello.newsService.service;

import hello.newsService.entity.News;
import hello.newsService.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.getAll();
    }

    @Override
    public News getNewsById(int id) {
        return newsRepository.getNews(id);
    }

    @Override
    public int addNews(News news) {
        return newsRepository.addNews(news);
    }

    @Override
    public void deleteNews(int id) {
        newsRepository.delNews(id);
    }

    @Override
    public byte[] getImageById(int id) {
        return newsRepository.getImageById(id);
    }
}