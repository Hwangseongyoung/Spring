package com.example.tut01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/admin.html";
    }
    @GetMapping("/employees")
    public String getEmp() {
        return "admin/employees.html";
    }
    @GetMapping("/article")
    public String getArticle() {
        return "admin/article.html";
    }
    @GetMapping("/site")
    public String getSite() {
        return "admin/site.html";
    }
}
