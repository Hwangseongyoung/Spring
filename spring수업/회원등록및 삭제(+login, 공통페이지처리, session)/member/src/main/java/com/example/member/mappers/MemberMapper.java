package com.example.member.mappers;

import com.example.member.dto.MemberDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface MemberMapper {
    @Insert("insert into member values(null, #{userid}, #{passwd}, #{username}, now())")
    public void setInsert(MemberDto memberDto);

    @Select("select * from member ${queryString} order by id desc")
    public List<MemberDto> getMemberList(String queryString);

    @Select("select count(*) from member ${queryString}")
    public int getMemberCount(String queryString);

    @Delete("delete from member where id = #{id}")
    public void deleteMember(int id);

    @Select("select * from member where id = #{id}")
    public MemberDto viewMember(int id);

    @Select("select * from member where userid = #{userid} and passwd = #{passwd}")
    public MemberDto setLogin(MemberDto memberDto);
}
