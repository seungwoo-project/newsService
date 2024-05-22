package hello.newsService.service;

import hello.newsService.entity.News;
import hello.newsService.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{


    private NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News addNews(News news) {
        return null;
    }

    @Override
    public List<News> getAllNews() {
        return null;
    }

    @Override
    public News getNewsById(int id) {
        return null;
    }

    @Override
    public void deleteNews(int id) {

    }
}
