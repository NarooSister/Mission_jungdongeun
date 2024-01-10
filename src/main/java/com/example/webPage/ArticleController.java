package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import com.example.webPage.entity.Boards;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public String article(
            @RequestParam(name = "boardsId", required = false, defaultValue = "1")
            Long boardsId,
            Model model) {
        String inputBoardsId;

        if (boardsId == 1) {
            inputBoardsId = "allBoards";
            model.addAttribute("articles", articleService.readAll());
        } else {
            if (boardsId == 2) {
                inputBoardsId = "freeBoards";
            }
            else if (boardsId == 3) {
                inputBoardsId = "devBoards";
            }
            else if (boardsId == 4) {
                inputBoardsId = "dailyBoards";
            }
            else if (boardsId == 5) {
                inputBoardsId = "accidentBoards";
            } else{
                return "redirect:/";
            }
            model.addAttribute("articles", articleService.findByBoardsBoardsId(boardsId));
        }
       /* return "boards/" + inputBoardsId;*/
        return "article/articleList";
    }

    @GetMapping("write")
    public String articleWrite() {
        return "article/write";
    }

    //CREATE
    //http post요청이 /article 경로로 들어왔을 때 이 메서드가 처리
    @PostMapping
    public String articleCreate(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("boardsId")
            Long boardsId
    ) {
        //받아온 데이터를 이용하여 ArticleDto을 생성하고
        // articleService.create 메서드를 이용해
        //실제로 새로운 게시글로 저장.
        //이렇게 생성된 게시글의 새 Id를 newId에 저장
        //성공적으로 생성되면 article/newId로 리다이렉트
        Long newId = articleService.create(new ArticleDto(title, content, password, boardsId)).getArticleId();
        return String.format("redirect:/article/%d", newId);
    }

    @GetMapping("{articleId}")
    public String articleOne(
            @PathVariable("articleId")
            Long articleId,
            Model model
    ) {
        System.out.println("다");
        model.addAttribute("article", articleService.read(articleId));
        return "article/articlePage";
    }

    @GetMapping("{articleId}/update")
    public String articleEdit(
            @PathVariable("articleId")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.read(id));
        return "article/update";
    }

    @PostMapping("{articleId}/update")
    public String articleUpdate(
            @PathVariable("articleId")
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
    @PostMapping("deleteArticle")
    public String articlePassword(
            Long articleId,
            Long boardsId,
            Model model) {

        model.addAttribute("articleId", articleId);
        model.addAttribute("boardsId", boardsId);
        return "article/deleteArticle";
    }

    //비밀번호 확인 후 삭제
    @PostMapping("{articleId}/deleteArticle")
    public String articleDelete(
            @PathVariable("articleId") Long articleId,
            @RequestParam("password") String password
    ) {
        // 비밀번호 확인 후 삭제
        articleService.delete(articleId, password);
        return "redirect:/article/{boardsId}";
    }
}