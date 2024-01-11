package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final BoardsService boardsService;

    @GetMapping("article/write")
    public String articleWrite() {
        return "article/write";
    }

    //CREATE
    //http post요청이 /article 경로로 들어왔을 때 이 메서드가 처리
    @PostMapping("article/create")
    public String createArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("password") String password,
            @RequestParam("boardsId") Long boardsId
    ) {
        // ArticleService를 통해 Article 생성
        Long newArticleId = articleService.create(new ArticleDto(title, content, password, boardsId)).getId();
        System.out.println(newArticleId);
        // 생성된 Article의 ID를 이용하여 상세 페이지로 리다이렉트
        return String.format("redirect:/article/%d", newArticleId);
    }

    @GetMapping("boards/{boardsId}")
    public String readAll(
            @PathVariable("boardsId")
            Long boardsId,
            Model model) {
        model.addAttribute("boards", boardsService.read(boardsId));
        /*if (boardsId == 1) {
            return "/allBoards";
        } else if (boardsId == 2) {
            return "/freeBoards";
        } else if (boardsId == 3) {
            return "/devBoards";
        } else if (boardsId == 4) {
            return "/dailyBoards";
        } else if (boardsId == 5) {
            return "/accidentBoards";
        } else {
            return "redirect:/";
        }*/

        String inputBoardsId;
        if (boardsId == 1) {
            inputBoardsId = "allBoards";
        } else if (boardsId == 2) {
            inputBoardsId = "freeBoards";
        } else if (boardsId == 3) {
            inputBoardsId = "devBoards";
        } else if (boardsId == 4) {
            inputBoardsId = "dailyBoards";
        } else if (boardsId == 5) {
            inputBoardsId = "accidentBoards";
        } else {
            return "redirect:/";
        }
        System.out.println("inputBoardsId = "+inputBoardsId);
        List<ArticleDto> articles;

        if ("allBoards".equals(inputBoardsId)) {
            articles = articleService.readAll();
        } else {
            articles = articleService.findByBoardsId(boardsId);
            System.out.println("findByBoardsId="+boardsId);
        }

        model.addAttribute("articles", articles);
        model.addAttribute("inputBoardsId", inputBoardsId);

        /*return "boards/" + inputBoardsId;*/


        //articleList에 하나의 페이지로 구현하는 경우
       return "article/articleList";
    }


    @GetMapping("article/{id}")
    public String read(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.read(id));
        return "article/articlePage";
    }

    @GetMapping("article/{id}/update")
    public String articleEdit(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.read(id));
        return "article/update";
    }

    @PostMapping("article/{id}/update")
    public String articleUpdate(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        articleService.update(id, password, new ArticleDto(title, content));
        return String.format("redirect:/article/%d", id);
    }

    // 삭제 페이지 이동
    @PostMapping("article/delete")
    public String articlePassword(
            Long id,
            Long boardsId,
            Model model) {
        model.addAttribute("articleId", id);
        model.addAttribute("boardsId", boardsId);
        return "article/deleteArticle";
    }

    //비밀번호 확인 후 삭제
    @PostMapping("article/{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id,
            @RequestParam("password")
            String password
    ) {
        // 비밀번호 확인 후 삭제
        articleService.delete(id, password);
        return "redirect:/articlePage";
    }
}