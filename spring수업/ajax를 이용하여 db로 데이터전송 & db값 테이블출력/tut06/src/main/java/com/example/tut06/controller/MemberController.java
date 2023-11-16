package com.example.tut06.controller;

import com.example.tut06.dto.MemberDto;
import com.example.tut06.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("")
    public String getList() {
        return "member/list.html";
    }

    @GetMapping("/add")
    public String getAdd() {
        return "member/add.html";
    }

    @PostMapping("/add")
    @ResponseBody
    public Map <String, Object> setAdd(@ModelAttribute MemberDto memberDto) {

        Map <String, Object> map = new HashMap<>();
        if(memberDto!=null) {
            memberMapper.setAdd(memberDto);
            map.put("msg", "success");
        }
        return map;
    }

    @GetMapping("/getMembers")
    @ResponseBody
    public Map <String, Object> getMembers() {
        List<MemberDto> list = memberMapper.getMembers();

        Map <String, Object> map = new HashMap<>();
        map.put("members", list);

        return map;
    }
}
