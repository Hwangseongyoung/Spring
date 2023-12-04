package com.example.board.mappers;

import com.example.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from member where userid = #{userid} and passwd = #{passwd}")
    MemberDto setLogin(MemberDto memberDto);
}
