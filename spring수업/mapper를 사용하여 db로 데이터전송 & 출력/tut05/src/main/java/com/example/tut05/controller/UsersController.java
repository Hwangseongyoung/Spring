package com.example.tut05.controller;

import com.example.tut05.dto.UsersDto;
import com.example.tut05.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersMapper um;

    @GetMapping("")
    public String getUsers(Model model) {
        List<UsersDto> list = um.getUsers();
        model.addAttribute("userList", list);
        return "users/list.html";
    }

    @GetMapping("/insert")
    public String getInsert() {
        return "users/insert.html";
    }

    @PostMapping("/insert")
    public String setInsert(@ModelAttribute UsersDto usersDto) {
        um.setInsert(usersDto);

//        값 저장 후 페이지 이동 redirect
        return "redirect:/users";
    }
}
