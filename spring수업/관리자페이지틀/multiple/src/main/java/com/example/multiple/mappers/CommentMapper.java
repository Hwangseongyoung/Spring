package com.example.multiple.mappers;

import com.example.multiple.dto.CommentDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment_${configCode} values(null, #{bId}, #{cSubject}, #{cWriter}, #{cComment}, 0, now())")
    public void setComment(CommentDto commentDto);

    @Select("select * from comment_${configCode} where b_id = #{bId}")
    public List<CommentDto> getCommentList(CommentDto commentDto);
}
