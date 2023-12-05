package com.example.multiple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {

    @GetMapping("/config/configList")
    public String getConfigList() {
        return "config/configList.html";
    }

    @GetMapping("/config/configWrite")
    public String getConfigWrite() {
        return "config/configWrite.html";
    }
}
