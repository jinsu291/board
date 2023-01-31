package com.ll.exam.board.member.repository;

import com.ll.exam.board.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberRepository {
    @Select("SELECT * FROM member WHERE username = #{username}")
    Member getMemberByUsername(String username);
}
