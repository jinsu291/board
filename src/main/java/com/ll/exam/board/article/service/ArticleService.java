package com.ll.exam.board.article.service;

import com.ll.exam.board.article.dto.Article;
import com.ll.exam.board.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public long write(String subject, String content) {
        articleRepository.write(subject, content);
        return articleRepository.getLastInsertId();
    }

    public Article getArticleById(long id) {
        return articleRepository.getArticleById(id);
    }

    public List<Article> getForPrintArticles() {
        return articleRepository.getForPrintArticles();
    }
}
