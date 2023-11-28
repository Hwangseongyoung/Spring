package com.example.board.mappers;

import com.example.board.dto.BoardDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select ifnull(max(grp) + 1, 1) as maxGrp from board")
    public int getMaxGrp();

    @Insert("INSERT INTO board VALUES(null, " +
            "#{subject}, #{writer}, #{content}, 0, now(), " +
            "#{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, " +
            "#{folderName}, #{ext}, #{grp}, 1, 1)")
    public void setWrite(BoardDto boardDto);

    @Select("select * from board order by id desc")
    public List<BoardDto> getList();

    @Select("select count(*) from board")
    public int getListCount();

    @Select("select * from board where id = #{id}")
    public BoardDto getView(int id);

    @Update("update board set visit = visit + 1 where id = #{id}")
    public void updateVisit(int id);
}
