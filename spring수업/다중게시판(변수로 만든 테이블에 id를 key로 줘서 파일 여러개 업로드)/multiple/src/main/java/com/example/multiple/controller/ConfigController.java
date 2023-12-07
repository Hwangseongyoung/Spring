package com.example.multiple.controller;

import com.example.multiple.dto.ConfigDto;
import com.example.multiple.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("/config/configList")
    public String getConfigList(Model model) {
        List<ConfigDto> config = configService.getConfigList();
        model.addAttribute("config", config);
        return "config/configList.html";
    }

    @GetMapping("/config/configWrite")
    public String getConfigWrite() {
        return "config/configWrite.html";
    }

    @GetMapping("/config/checkConfigCode")
    @ResponseBody
    public Map<String, Object> getCheckConfigCode(@RequestParam String configCode) {
        int checkCode = configService.getCheckConfigCode(configCode);
        Map<String, Object> map = new HashMap<>();
        map.put("checkCode", checkCode);
        return map;
    }

    @PostMapping("/config/configWrite")
    public String setConfigWrite(@ModelAttribute ConfigDto configDto) {
        configService.setConfig(configDto);
        configService.makeBoard(configDto.getConfigCode());
        configService.makeFiles(configDto.getConfigCode());
        configService.makeComment(configDto.getConfigCode());
        return "redirect:/config/configList";
    }

    @GetMapping("/config/colorChange")
    @ResponseBody
    public Map<String, Object> getColorChange(int id, String color) {
        Map<String, Object> map = new HashMap<>();
        if(id>0 && !color.equals("")) {
            configService.getColorChange(id, color);
            map.put("msg", "success");
        }else {
            map.put("msg", "failure");
        }
        return map;
    }

    @GetMapping("/config/deleteConfig")
    @ResponseBody
    public Map<String, Object> deleteConfig(@RequestParam String configCode) {
        Map<String, Object> map = new HashMap<>();
        if(!configCode.isEmpty()) {
            configService.deleteConfig(configCode);
            configService.dropBoard(configCode);
            configService.dropFiles(configCode);
            configService.dropComment(configCode);
            map.put("msg", "success");
        }else {
            map.put("msg", "failure");
        }
        return map;
    }
}
