package com.example.tut00.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {
    @GetMapping
    public String getHome(Model model) { // model 객체 데이터
        model.addAttribute("title", "Hello, Koreait");
        model.addAttribute("intro", "코리아아이티 아카데미에 오신 것을 환영합니다.");
        return "index.html";
    }
    @GetMapping("/company")
    public String getCompany(Model model) {
        model.addAttribute("title", "Company");
        model.addAttribute("subTitle", "회사소개");
        return "sub/company.html";
    }
}
