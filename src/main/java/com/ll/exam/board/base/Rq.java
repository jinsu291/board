package com.ll.exam.board.base;

import com.ll.exam.board.member.dto.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;
    @Getter
    private String alertMsg;

    public String getCurrentUrl() {
        String url = req.getRequestURL().toString();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            url += "?" + queryString;
        }

        return url;
    }

    public boolean isLogined() {
        return getLoginedMemberId() > 0;
    }

    public boolean isLogout() {
        return isLogined() == false;
    }

    public long getLoginedMemberId() {
        Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");

        if (loginedMemberId == null) return 0;

        return loginedMemberId;
    }

    public String getLoginedMemberUsername() {
        return (String) session.getAttribute("loginedMemberUsername");
    }

    public String getLoginedMemberName() {
        return (String) session.getAttribute("loginedMemberName");
    }

    public String getLoginedMemberEmail() {
        return (String) session.getAttribute("loginedMemberEmail");
    }

    private String getLoginedMemberRoles() {
        return (String) session.getAttribute("loginedMemberRoles");
    }

    public Member getLoginedMember() {
        long id = getLoginedMemberId();
        String username = getLoginedMemberUsername();
        String roles = getLoginedMemberRoles();
        String name = getLoginedMemberName();
        String email = getLoginedMemberEmail();

        return Member
                .builder()
                .id(id)
                .username(username)
                .name(name)
                .email(email)
                .roles(roles)
                .build();
    }

    public void setLoginDone(Member member) {
        session.setAttribute("loginedMemberId", member.getId());
        session.setAttribute("loginedMemberRoles", member.getRoles());
        session.setAttribute("loginedMemberUsername", member.getUsername());
        session.setAttribute("loginedMemberName", member.getName());
        session.setAttribute("loginedMemberEmail", member.getEmail());
    }

    public void setLogoutDone() {
        session.removeAttribute("loginedMemberId");
        session.removeAttribute("loginedMemberRoles");
        session.removeAttribute("loginedMemberUsername");
        session.removeAttribute("loginedMemberName");
        session.removeAttribute("loginedMemberEmail");
    }

    public String historyBackTemplate(String msg) {
        alertMsg = msg;

        return "common/js";
    }

    public boolean isAdmin() {
        if (isLogout()) return false;

        return getLoginedMember().hasRole("ADMIN");
    }
}