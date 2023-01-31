package com.ll.exam.board.article.dto;

import lombok.Data;

@Data
public class Article {
    private long id;
    private String subject;
    private String content;
    private long memberId;
}