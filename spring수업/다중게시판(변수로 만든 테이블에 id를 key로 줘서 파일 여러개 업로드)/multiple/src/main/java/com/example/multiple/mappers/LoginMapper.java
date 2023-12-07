package com.example.multiple.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select count(*) from member where userid = #{userid}")
    public int idCheck(String userid);
}
