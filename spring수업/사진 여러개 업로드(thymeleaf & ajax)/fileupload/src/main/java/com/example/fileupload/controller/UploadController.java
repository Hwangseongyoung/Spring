package com.example.fileupload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/upload")
    public String getUpload() {
        return "upload/uploadForm.html";
    }

    @PostMapping("/upload")
    public String setUpload(Model model, @RequestParam("file") MultipartFile file) throws IOException {
//        저장을 위한 폴더 생성(날짜를 이용해서 생성)
//        파일, 폴더 만드는 준비 객체
        String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        File makeFolder = new File(fileDir + folderName);

//        make dir
        if(!makeFolder.exists()) {
            makeFolder.mkdir();
        }

//        make file
        String oriName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String ext = oriName.substring(oriName.lastIndexOf("."));

        String saveFileName = uuid + ext; // 이미지 표시할때
        String savePathFileName = fileDir + folderName + "/" + saveFileName; // 업로드 할때

        file.transferTo(new File(savePathFileName));

        model.addAttribute("folderName", folderName);
        model.addAttribute("saveFileName", saveFileName);

        model.addAttribute("savePathFileName", savePathFileName);
        return "upload/uploadView.html";
    }

    @GetMapping("/view")
    public String getUploadView() {
        return  "upload/uploadView.html";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getDownload(@RequestParam String savePathFileName) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:" + savePathFileName);
        String encodedFileName = UriUtils.encode(savePathFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; fileName=\""+encodedFileName+"\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }
}
