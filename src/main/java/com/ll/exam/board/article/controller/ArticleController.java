package com.ll.exam.board.article.controller;

import com.ll.exam.board.article.dto.Article;
import com.ll.exam.board.article.form.ArticleForm;
import com.ll.exam.board.article.service.ArticleService;
import com.ll.exam.board.member.dto.Member;
import com.ll.exam.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<Article> articles = articleService.getForPrintArticles();

        model.addAttribute("articles", articles);
        return "article/list";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id) {
        return "article/detail";
    }

    @GetMapping("/create")
    public String articleCreate(ArticleForm articleForm) {
        return "article/form.html";
    }

    @PostMapping("/create")
    public String articleCreate(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "article/form.html";
        }
        Member member = this.memberService.getMemberByUsername(principal.getName());
        this.articleService.create(articleForm.getSubject(), articleForm.getContent());
        return "redirect:/article/list";
    }
}