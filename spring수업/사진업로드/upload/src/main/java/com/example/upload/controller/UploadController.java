package com.example.upload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/upload")
    public String getUpload() {
        return "upload/upload.html";
    }

    @GetMapping("/view")
    public String getView() {
        return "upload/view.html";
    }

    @PostMapping("/upload")
    public String setUpload(@RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {
//        String fileDir = "C:/Users/dfghu/OneDrive/바탕 화면/국비수업/Spring/spring수업/사진업로드/upload/src/main/resources/static/upload/";

//        업로드
//        System.out.println(multipartFile.getOriginalFilename());
//        System.out.println(multipartFile.getSize());

//        파일 중복 -> 변경(uuid) -> 확장자 기준으로 배열 0 -> uuid 변경 -> 저장
//        UUID 임의의 난수 생성
        String uuid = UUID.randomUUID().toString();
//        System.out.println(uuid);

//        .을 기준으로 indexOf(".")
//        자르기 substring
        String origName = multipartFile.getOriginalFilename();
        String ext = origName.substring(origName.lastIndexOf("."));
//        System.out.println(ext);

        String fileName = uuid + ext;
//        System.out.println(saveFileName);

        String savePathFile = fileDir + fileName;
//        System.out.println(savePathFile);

        multipartFile.transferTo((new File(savePathFile)));

        model.addAttribute("filename", fileName);

        return "upload/view.html";
    }
}
