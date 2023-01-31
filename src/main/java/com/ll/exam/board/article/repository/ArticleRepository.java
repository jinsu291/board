package com.ll.exam.board.article.repository;

import com.ll.exam.board.article.dto.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleRepository {

    @Select("SELECT * FROM article")
    public List<Article> getArticles();

    @Insert("INSERT INTO article SET createDate = NOW(), modifyDate = NOW(), subject = #{subject}, content = #{content}")
    void write(String subject, String content);

    @Select("SELECT LAST_INSERT_ID()")
    public long getLastInsertId();

    @Select("SELECT * FROM article WHERE id = #{id}")
    Article getArticleById(long id);
}