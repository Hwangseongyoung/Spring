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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/addItem")
    public String getAddItem() {
        return "admin/addItem.html";
    }

    @PostMapping("/addItem")
    @ResponseBody
    public Map<String, Object> setAddItem(@ModelAttribute ItemsDto itemsDto) {
        Map<String, Object> map = new HashMap<>();
        if(itemsDto != null) {
            itemMapper.setAddItem(itemsDto);
            map.put("msg", "success");
        }else {
            map.put("msg", "failure");
        }
        return map;
    }

    @GetMapping("/deleteItem")
    @ResponseBody
    public Map<String, Object> deleteItem(@RequestParam int id) {
        Map<String, Object> map = new HashMap<>();
        if(id > 0) {
        itemMapper.deleteItem(id);
        map.put("msg", "success");
        }else {
            map.put("msg", "failure");
        }
        return map;
    }

    @GetMapping("/updateItem")
    public String getUpdateItem(@RequestParam int id, Model model) {
        ItemsDto result = itemMapper.viewItem(id);
        model.addAttribute("item", result);
        return "admin/updateItem.html";
    }

    @PostMapping("/updateItem")
    @ResponseBody
    public Map<String, Object> setUpdateItem(@ModelAttribute ItemsDto itemsDto) {

        Map<String, Object> map = new HashMap<>();
        if(itemsDto != null) {
            itemMapper.setUpdateItem(itemsDto);
            map.put("msg", "success");
        }else {
            map.put("msg", "failure");
        }
        return map;
    }
}
