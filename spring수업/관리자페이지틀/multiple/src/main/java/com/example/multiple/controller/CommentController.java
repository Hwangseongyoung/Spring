package com.example.multiple.controller;

import com.example.multiple.dto.CommentDto;
import com.example.multiple.mappers.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @GetMapping("/comment/commentWrite")
    @ResponseBody
    public Map<String, Object> setCommentWrite(@ModelAttribute CommentDto commentDto) {
        Map<String, Object> map = new HashMap<>();
        if(commentDto != null) {
            commentMapper.setComment(commentDto);
            map.put("msg", "success");
        }else {
            map.put("msg", "failure");
        }
        return map;
    }

    @GetMapping("/comment/commentList")
    @ResponseBody
    public Map<String, Object> getCommentList(@ModelAttribute CommentDto commentDto) {
        Map<String, Object> map = new HashMap<>();
        List<CommentDto> list = commentMapper.getCommentList(commentDto);
        map.put("cList", list);
        return map;
    }
}
