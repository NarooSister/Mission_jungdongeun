package com.example.webPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("boards")
public class BoardsController {

    //게시판 목록을 보여주는 페이지
    @GetMapping
    public String boardsList(Model model){
        return "boards/home";
    }
//    @GetMapping("/{boardsId}")
//    public String showBoard(@PathVariable Long boardsId, Model model) {
//        // 여기서는 해당 게시판의 정보를 전달
//        String boardName = BoardsService.getBoardNameById(boardsId);
//        model.addAttribute("boardName", boardName);
//
//        // 게시글 목록을 전달하는 로직도 추가 가능
//
//        return "article/articleList";
//    }


}

