package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.mappers.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
public class BoardController {

    @Value("${fileDir}")
    String fileDir;

    @Autowired
    BoardMapper boardMapper;

    @GetMapping("/board/list")
    public String getList(Model model) {
        model.addAttribute("cnt", boardMapper.getListCount());
        model.addAttribute("list", boardMapper.getList());
        return "board/list.html";
    }

    @GetMapping("/board/write")
    public  String getWrite() {
        return  "board/write.html";
    }

    @PostMapping("/board/write")
    public String setWrite(@ModelAttribute BoardDto boardDto, @RequestParam("file")MultipartFile mf) throws IOException {

        if(boardDto != null) {
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if(!mf.isEmpty()) {
                if(!makeFolder.exists()) {
                    makeFolder.mkdir();
                }

                String orgName = mf.getOriginalFilename();
                String ext = orgName.substring(orgName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                String saveFileName = uuid + ext;
                String savedFilePathName = fileDir + folderName + "/" + saveFileName;

    //            boardDto => db
                boardDto.setOrgName(orgName);
                boardDto.setSavedFileName(saveFileName);
                boardDto.setSavedFilePathName(savedFilePathName);
                boardDto.setSavedFileSize(mf.getSize());
                boardDto.setFolderName(folderName);
                boardDto.setExt(ext);

                /* 파일 업로드 쓰기 */
                mf.transferTo( new File(savedFilePathName) );
            }

            int maxGrp = boardMapper.getMaxGrp();
            boardDto.setGrp(maxGrp);
            boardMapper.setWrite(boardDto);
        }
        return "redirect:/board/list";
    }

    @GetMapping("/board/view")
    public String getView(@RequestParam int id, Model model) {
        boardMapper.updateVisit(id);
        model.addAttribute("view", boardMapper.getView(id));
        return "board/view.html";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getDownload(@RequestParam String fileName) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:" + fileName);
        String encodedFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; fileName=\""+encodedFileName+"\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }
}
