package com.ll.exam.board.article.repository;

import com.ll.exam.board.article.dto.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleRepository {

    @Select("SELECT * FROM article")
    public List<Article> getArticles();
}