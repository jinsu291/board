package com.ll.exam.board.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Rq {
    @Getter
    private int count;
    @Getter
    @Setter
    private String name;

    public void increaseCount() {
        count++;
    }
}