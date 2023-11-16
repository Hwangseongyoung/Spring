package com.example.tut02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("")
    public String getIndex() {
        return "admin/index.html";
    }

    @GetMapping("/employees")
    public String getEmp() {
        return "admin/employees.html";
    }

    @GetMapping("/article")
    public String getArt() {
        return "admin/article.html";
    }
}
