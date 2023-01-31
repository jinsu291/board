package com.ll.exam.board.member.service;

import com.ll.exam.board.member.dto.Member;
import com.ll.exam.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMemberByUsername(String username) {
        return memberRepository.getMemberByUsername(username);
    }
}
