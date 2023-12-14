package com.example.multiple.mappers;

import com.example.multiple.dto.BoardDto;
import com.example.multiple.dto.FileDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    @Select("select ifnull(max(grp) + 1, 1) from board_${configCode}")
    public int getGrpMaxCnt(String configCode);

    @Insert("insert into board_${configCode} values(null, #{subject}, #{writer}, #{content}, 0, now(), #{grp}, #{seq}, 1, #{isFiles})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void setBoard(BoardDto boardDto);

    @Insert("insert into files_${configCode} values(#{id}, #{orgName}, #{savedFileName}, #{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFiles(FileDto fileDto);

    @Select("select * from board_${configCode} ${searchQuery} order by grp desc, seq asc limit #{startNum}, #{offset}")
    public List<BoardDto> getBoardList(Map<String, Object> map);

    @Select("select * from board_${configCode} where id = #{id}")
    public BoardDto getBoard(String configCode, int id);

    @Select("select * from files_${configCode} where id = #{id}")
    public List<FileDto> getFiles(String configCode, int id);

    @Delete("delete from board_${configCode} where id = #{id}")
    public void getBoardDelete(BoardDto boardDto);

    @Delete("delete from files_${configCode} where id = #{id}")
    public void setFilesDelete(BoardDto boardDto);

    @Delete("delete from files_${configCode} where b_id = #{bId}")
    public void setCommentDelete(BoardDto boardDto);

    @Select("select count(*) from board_${configCode} ${searchQuery}")
    public int getBoardCount(String configCode, String searchQuery);

    @Select("select * from files_${configCode} where savedFileName = #{savedFileName}")
    FileDto getFile(String configCode, String savedFileName);

//    계층형 게시판에서 답글 순서를 변경하는 업데이트 작업
    @Update("update board_${configCode} set seq = seq + 1 where grp = #{grp} and seq > #{seq}")
    public void setReplyUpdate(BoardDto boardDto);
}
