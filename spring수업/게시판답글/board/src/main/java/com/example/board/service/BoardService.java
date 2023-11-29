package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.mappers.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<BoardDto> getSearch(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
//        searchType
//        subject, writer => where =
//        content where like
//        where content like '%비서실%';
        if(searchType.equals("subject") || searchType.equals("writer")) {
            searchQuery = "where " + searchType + " = '" + words + "'";
        }else if(searchType.equals("content")) {
            searchQuery="where "+searchType+" like '%"+ words +"%'";
        }else {
            searchQuery="";
        }
        System.out.println(searchQuery);
        map.put("searchQuery", searchQuery);

        return boardMapper.getList(map);
    }

    public int getSearchCnt(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
//        searchType
//        subject, writer => where =
//        content where like
//        where content like '%비서실%';
        if(searchType.equals("subject") || searchType.equals("writer")) {
            searchQuery = "where " + searchType + " = '" + words + "'";
        }else if(searchType.equals("content")) {
            searchQuery="where "+searchType+" like '%"+ words +"%'";
        }else {
            searchQuery="";
        }
        System.out.println(searchQuery);
        map.put("searchQuery", searchQuery);

        return boardMapper.getListCount(map);
    }

    public void setDelete(int id) {
//        db
//        boardMapper.setDelete(id);

//        file
//        BoardDto b = boardMapper.getView(id);

        if(id > 0) {

            BoardDto bd = boardMapper.getView(id);
            boardMapper.setDelete(id);

//            절대경로
            String removeFile = bd.getSavedFilePathName(); // c 절대경로 + 파일이름
//            String removeFile1 = "src/main/resources/static/upload/"+bd.getFolderName() +"/"+bd.getSavedFileName();
//            File F = new File(removeFile);
//            File객체는 생성 또는 삭제할 준비
            File f = new File(removeFile);
//            File f = new File(removeFile1);

            boolean result = false;
            if (f.exists()) {
                result = f.delete();
            }
            if (result) {
                System.out.println("파일이 삭제되었습니다.");
            } else {
                System.out.println("파일이 존재하지 않습니다.");
            }

        }
    }
}
