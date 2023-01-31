package com.ll.exam.board;

import com.ll.exam.board.article.dto.Article;
import com.ll.exam.board.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class BoardApplicationTests {
	@Autowired
	private ArticleService articleService;

	@Test
	@DisplayName("게시물 리스트 반환")
	void t1() {
		List<Article> articles = articleService.getArticles();
		System.out.println(articles);
	}
	// 나중에 해보기
	@Test
	@DisplayName("게시물 작성")
	void t2() {
		articleService.write("제목3", "내용3");
	}
}