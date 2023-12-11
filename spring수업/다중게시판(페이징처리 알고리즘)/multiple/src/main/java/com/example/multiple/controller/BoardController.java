package com.example.multiple.controller;

import com.example.multiple.dto.BoardDto;
import com.example.multiple.dto.FileDto;
import com.example.multiple.mappers.ConfigMapper;
import com.example.multiple.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
public class BoardController {

    @Value("${fileDir}")
    String fileDir;

    @Autowired
    BoardService boardService;

    @Autowired
    ConfigMapper configMapper;

    @GetMapping("/board/boardList")
    public String getBoardList(@RequestParam String configCode, Model model, @RequestParam(value="page", defaultValue = "1") int page) {
        model.addAttribute("configCode", configCode);
        model.addAttribute("board", boardService.getBoardList(configCode, page));
        model.addAttribute("config", configMapper.getBoardConfig(configCode));
        return "board/boardList.html";
    }

    @GetMapping("/board/boardWrite")
    public String getBoardWrite(@RequestParam String configCode, Model model) {
        model.addAttribute("configCode", configCode);
        return "board/boardWrite.html";
    }

    @PostMapping("/board/boardWrite")
    public String setBoardWrite(Model model, @ModelAttribute BoardDto boardDto, @RequestParam("files") List<MultipartFile> files) throws IOException {

        int grp = boardService.getGrpMaxCnt(boardDto.getConfigCode());
        boardDto.setGrp(grp);

        if(!files.get(0).isEmpty()) {
            boardDto.setIsFiles("Y");
            boardService.setBoard(boardDto);
            int fileID = boardDto.getId();
//            20231207
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
//            folder, file 생성
            File makeFolder = new File(fileDir + folderName);
            if(!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            FileDto fileDto = new FileDto();
            for(MultipartFile mf : files) {
//                경로명 + uuid
                String savedPathName = fileDir + folderName;

                String orgName = mf.getOriginalFilename();
                String ext = orgName.substring(orgName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;

                mf.transferTo(new File(savedPathName + "/" + savedFileName));

                fileDto.setConfigCode(boardDto.getConfigCode());
                fileDto.setId(fileID);
                fileDto.setOrgName(orgName);
                fileDto.setSavedFileName(savedFileName);
                fileDto.setSavedPathName(savedPathName);
                fileDto.setFolderName(folderName);
                fileDto.setExt(ext);

                boardService.setFiles(fileDto);
            }
        }else {
            boardService.setBoard(boardDto);
        }

//        if(boardDto != null) {
//            int grp = boardService.getGrpMaxCnt(boardDto.getConfigCode());
//            boardDto.setGrp(grp);
//            boardService.setBoard(boardDto);
//        }

        model.addAttribute("configCode", boardDto.getConfigCode());

        return "redirect:/board/boardList?configCode="+boardDto.getConfigCode();
    }

    @GetMapping("/board/boardView")
    public String getBoardView(@RequestParam String configCode, @RequestParam int id, Model model) {

        model.addAttribute("board", boardService.getBoard(configCode, id));
        model.addAttribute("files", boardService.getFiles(configCode, id));
        model.addAttribute("configCode", configCode);
        model.addAttribute("config", configMapper.getBoardConfig(configCode));

        return "board/boardView.html";
    }

    @GetMapping("/board/boardDelete")
    public String getBoardDelete(@ModelAttribute BoardDto boardDto) {
        if(!boardDto.getConfigCode().isEmpty() && boardDto.getId() > 0) {
            boardService.getBoardDelete(boardDto); // 내용 디비 삭제

//            파일 삭제
            List<FileDto> files = boardService.getFiles(boardDto.getConfigCode(), boardDto.getId());
            for(FileDto fd : files) {
                File file = new File(fd.getSavedPathName() + "/" + fd.getSavedFileName());
                file.delete();
            }

//            파일 디비 삭제
            boardService.setFilesDelete(boardDto);
        }

        return "redirect:/board/boardList?configCode="+boardDto.getConfigCode();
    }
}
