package com.example.crud.controller;

import com.example.crud.dto.ItemsDto;
import com.example.crud.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("")
    public String getListItem() {
        return "users/listItem.html";
    }

    @PostMapping("")
    @ResponseBody
    public Map<String, Object> getListInfo() {
//        System.out.println(itemMapper.getCount());
//        System.out.println(itemMapper.getItemList());
        Map<String, Object> map = new HashMap<>();
        map.put("total", itemMapper.getCount());
        map.put("items", itemMapper.getItemList());

        return map;
    }

    @GetMapping("/viewItem")
    public String viewItem(@RequestParam int id, Model model) {
        ItemsDto result = itemMapper.viewItem(id);
        System.out.println(result);
        model.addAttribute("item", result);

        return "users/viewItem.html";
    }
}
