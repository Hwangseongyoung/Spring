package com.example.multiple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board/boardList")
    public String getBoardList() {
        return "board/boardList.html";
    }
}
