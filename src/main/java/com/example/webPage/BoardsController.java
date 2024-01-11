package com.example.webPage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardsController {
    private final BoardsService boardsService;

    //index화면. 게시판 이름들이 보인
    @GetMapping("/")
    public String readAll(Model model) {
        model.addAttribute("boards", boardsService.readAll());
        return "index";
    }
}