package com.example.tut03.controller;

import com.example.tut03.dto.RegDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegController {
    @GetMapping("/register")
    public String getRegister() {
        return "users/register.html";
    }

    @PostMapping("/register")
    public void setRegister(@ModelAttribute RegDto regDto) {
        System.out.println(regDto);
    }
}
