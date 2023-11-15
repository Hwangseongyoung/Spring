package com.example.tut04.controller;

import com.example.tut04.dto.RegDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class RegController {
//    a, 브라우저 url에 직접 입력 처리 -> GET
    @GetMapping("/register")
    public String getReg() {
        return "users/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> setReg(@ModelAttribute RegDto r) {
        System.out.println(r);

        Map<String, Object> map = new HashMap<>();

//        성공(200), 서버에러(500)
        map.put("status", "200");
        map.put("msg", "데이터가 전송되었습니다.");

        return map;
    }
}
