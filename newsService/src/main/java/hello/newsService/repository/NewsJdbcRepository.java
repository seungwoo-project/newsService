package hello.newsService.repository;

import hello.newsService.entity.News;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsJdbcRepository implements NewsRepository{
    Connection conn = null; // 데이터베이스 연결을 나타냄
    PreparedStatement pstmt; // sql쿼리를 실행하는데 사용됨
    final String JDBC_DRIVER = "org.h2.Driver"; // 드라이버 클래스 이름
    final String JDBC_URL = "jdbc:h2:tcp://localhost/~/newsdb"; // 데이터베이스 url

    // 데이터베이스 열기
    public void open() {
        try {
            Class.forName(JDBC_DRIVER); // JDBC 드라이버 로드
            conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234"); // 데이터베이스 연결
        }catch (Exception e) { e.printStackTrace(); }
    }

    // 데이터베이스 닫기
    public void close() {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }catch (SQLException e) { e.printStackTrace(); }
    }

    // 데이터베이스 삽입
    public void addNews(News n) {
        open();
        int result = 0;
        String sql = "INSERT INTO news(title, image, date, content) values (?,?,CURRENT_TIMESTAMP(0),?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, n.getTitle());
            pstmt.setBytes(2, n.getImage());
            pstmt.setString(3, n.getContent());
            result = pstmt.executeUpdate();
        }catch (SQLException e) { throw new RuntimeException("뉴스 등록 중 데이터베이스 오류가 발생했습니다.", e); }
        finally { close(); }

    }

    // 데이터베이스 조회
    public List<News> getAll()  {
        open();
        List<News> newsList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select id, title, image, "
                    + "PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss') as cdate, "
                    + "content from news");
            try (ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    News n = new News();
                    n.setId(rs.getInt("id"));
                    n.setContent(rs.getString("content"));
                    n.setTitle(rs.getString("title"));
                    n.setDate(rs.getString("cdate"));
                    n.setImage(rs.getBytes("image"));
                    newsList.add(n);
                }
            }

        }catch (SQLException e) { e.printStackTrace(); }
        finally { close(); }
        return newsList;
    }


    public News getNews(int id) {
        News news = null;
        open();

        String sql = "SELECT id, title, image, "
                + "PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss') as cdate, "
                + "content FROM news WHERE id = ?";


        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);


            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setImage(rs.getBytes("image"));
                    news.setDate(rs.getString("cdate"));
                    news.setContent(rs.getString("content"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return news;
    }

    public void delNews(int id) {
        open();
        String sql = "DELETE FROM news WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted == 0) {
                throw new SQLException("News article with id " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public byte[] getImageById(int id) {
        open(); // 데이터베이스 연결 열기
        byte[] imageBytes = null; // 이미지 바이트 배열 저장 변수
        ResultSet rs = null; // 결과셋 객체
        String sql = "SELECT image FROM news WHERE id = ?"; // SQL 쿼리
        try {
            pstmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
            pstmt.setInt(1, id); // 첫 번째 파라미터 설정
            rs = pstmt.executeQuery(); // 쿼리 실행 및 결과 처리
            if (rs.next()) { // 결과가 존재하면
                imageBytes = rs.getBytes("image");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(); // 데이터베이스 연결 닫기
        }
        return imageBytes; // 이미지 바이트 배열 반환
    }
}
