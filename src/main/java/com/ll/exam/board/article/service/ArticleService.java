package com.ll.exam.board.article.service;

import com.ll.exam.board.article.dto.Article;
import com.ll.exam.board.article.repository.ArticleRepository;
import com.ll.exam.board.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public long create(String subject, String content) {
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
