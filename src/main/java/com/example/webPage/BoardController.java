package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import com.example.webPage.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("boards", boardService.readAll());
        return "index";
    }

    @GetMapping("/board")
    public String listAllBoards(
            Model model
    ) {
        model.addAttribute("boards", boardService.readAll());
        //boardId가 1일때 readAll
        model.addAttribute("selected", 1);
        List<ArticleDto> articles = articleService.readAll();
        Collections.reverse(articles);
        model.addAttribute("articles", articles);
        return "board";
    }

    @GetMapping("/board/{boardId}")
    public String listOneBoard(
            @PathVariable("boardId")
            Long boardId,
            Model model
    ) {
        BoardDto boardDto = boardService.read(boardId);
        model.addAttribute("boards", boardService.readAll());
        model.addAttribute("selected", boardDto);
        List<ArticleDto> articles = boardDto.getArticles();
        Collections.reverse(articles);
        model.addAttribute("articles", articles);
        return "board";

    }
}