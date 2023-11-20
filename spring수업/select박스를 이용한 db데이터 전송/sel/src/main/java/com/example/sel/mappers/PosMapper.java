package com.example.sel.mappers;

import com.example.sel.dto.PosDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PosMapper {
    @Insert("insert into pos values(#{posCode},#{posName},#{deptCode})")
    public void addPos(PosDto posDto);

    @Select("select * from pos where dept_code = #{deptCode}")
    public List<PosDto> getPos(String deptCode);
}
