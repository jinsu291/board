package com.ll.exam.board.member.controller;

import com.ll.exam.board.base.Rq;
import com.ll.exam.board.member.dto.Member;
import com.ll.exam.board.member.service.MemberService;
import com.ll.exam.board.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/member/me")
    @ResponseBody
    public Member getMe() {
        return rq.getLoginedMember();
    }

    @GetMapping("/member/mypage")
    public String showMypage(String username) {
        Member member = memberService.getMemberByUsername(username);
        return "member/mypage";
    }

    @GetMapping("/member/login")
    public String showLogin() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(String username, String password) {
        Member member = memberService.getMemberByUsername(username);

        if (member == null) {
            return rq.historyBackTemplate("일치하는 회원이 없습니다.");        }

        if (member.matchPassword(password) == false) {
            return rq.historyBackTemplate("일치하는 회원이 없습니다.");        }

        rq.setLoginDone(member);

        return "redirect:/?msg=" + Util.url.encode("로그인 성공");
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {

        rq.setLogoutDone();

        return "redirect:/?msg=" + Util.url.encode("로그아웃 성공");
    }
}