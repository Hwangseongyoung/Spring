package com.example.board.mappers;

import com.example.board.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    @Select("select ifnull(max(grp) + 1, 1) as maxGrp from board")
    public int getMaxGrp();

    @Insert("INSERT INTO board VALUES(null, " +
            "#{subject}, #{writer}, #{content}, 0, now(), " +
            "#{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, " +
            "#{folderName}, #{ext}, #{grp}, 1, 1)")
    public void setWrite(BoardDto boardDto);

    @Insert("INSERT INTO board VALUES(null, " +
            "#{subject}, #{writer}, #{content}, 0, now(), " +
            "#{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, " +
            "#{folderName}, #{ext}, #{grp}, #{seq}, #{depth})")
    public void setReply(BoardDto boardDto);

    @Select("select * from board ${searchQuery} order by grp desc, seq asc limit #{startNum}, #{offset}")
    public List<BoardDto> getList(Map<String, Object> map);

    @Select("select count(*) from board ${searchQuery}")
    public int getListCount(Map<String, Object> map);

    @Select("select count(*) from board")
    public int totalCount();

    @Select("select * from board where id = #{id}")
    public BoardDto getView(int id);

    @Update("update board set visit = visit + 1 where id = #{id}")
    public void updateVisit(int id);

    @Delete("delete from board where id = #{id}")
    public void setDelete(int id);

    @Update("update board set subject = #{subject}," +
            " writer = #{writer}," +
            " content = #{content}," +
            " regdate = now()," +
            " orgName = #{orgName}," +
            " savedFileName = #{savedFileName}," +
            " savedFilePathName = #{savedFilePathName}," +
            " savedFileSize = #{savedFileSize}," +
            " folderName = #{folderName}," +
            " ext = #{ext} where id = #{id}")
    public void setUpdate(BoardDto boardDto);
}
