package com.example.sel.controller;

import com.example.sel.dto.DeptDto;
import com.example.sel.dto.PosDto;
import com.example.sel.mappers.DeptMapper;
import com.example.sel.mappers.PosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SelController {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private PosMapper posMapper;

    @GetMapping("/dept/sel")
    public String getDept() {
        return "dept/sel.html";
    }

    @PostMapping("/dept/sel")
    @ResponseBody
    public Map<String, Object> setDept() {

        Map<String, Object> map = new HashMap<>();
        map.put("dept", deptMapper.getDeptAll());

        return map;
    }

    @PostMapping("/dept/addDept")
    @ResponseBody
    public Map<String, Object> addDept(@ModelAttribute DeptDto deptDto) {
        Map<String, Object> map = new HashMap<>();

        if(deptDto != null) {
        deptMapper.addDept(deptDto);
        map.put("msg", "success");
        }

        return map;
    }

    @GetMapping("/dept/multi")
    public String getMulti() {
        return "dept/multiple.html";
    }

    @PostMapping("/pos/addPos")
    @ResponseBody
    public Map<String, Object> addPos(@ModelAttribute PosDto posDto) {

        System.out.println(posDto);
        Map<String, Object> map = new HashMap<>();
        posMapper.addPos(posDto);
            map.put("msg", "success");
        return map;
    }

    @GetMapping("/selbox")
    public String getSelbox() {
        return "selbox.html";
    }

    @PostMapping("/pos/getPos")
    @ResponseBody
    public Map<String, Object> getPos(@RequestParam String deptCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("pos", posMapper.getPos(deptCode));

        return map;
    }
}
