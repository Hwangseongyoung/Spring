package com.example.tut06.mappers;

import com.example.tut06.dto.MemberDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Insert("insert into member values(null, #{userid}, #{passwd})")
    public void setAdd(MemberDto memberDto);

    @Select("select * from member order by num desc")
    public List<MemberDto> getMembers();
}
