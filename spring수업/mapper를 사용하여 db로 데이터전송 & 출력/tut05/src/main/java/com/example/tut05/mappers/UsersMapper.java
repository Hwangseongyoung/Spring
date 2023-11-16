package com.example.tut05.mappers;

import com.example.tut05.dto.UsersDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper{ // mapper -> query
    @Insert("insert into users values(#{email}, #{passwd})")
    public void setInsert(UsersDto usersDto);

    @Select("select * from users")
    public List<UsersDto> getUsers();
}
