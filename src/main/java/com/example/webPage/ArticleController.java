package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequiredArgsConstructor

public class ArticleController {
    private final ArticleService articleService;
    private final BoardService boardService;

    //새 글 작성 페이지로 이동
    @GetMapping("article/write")
    public String articleWrite(
            Model model
    ) {
        model.addAttribute("boards", boardService.readAll());
        //Model에 board에 어떤 게시판이 존재 하는지 넣어주면
        //게시글 작성 시 게시판 고르기에서 사용 가능하다.
        return "article/write";
    }

    //CREATE
    //http post요청이 /article 경로로 들어왔을 때 이 메서드가 처리
    @PostMapping("article")
    public String createArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("password") String password,
            @RequestParam("board-Id") Long boardId
    ) {
        // ArticleService를 통해 Article 생성
        Long newArticleId = articleService.create(boardId, new ArticleDto(title, content, password)).getId();
        // 생성된 Article의 ID를 이용하여 상세 페이지로 리다이렉트
        return String.format("redirect:/article/%d", newArticleId);
    }




    @GetMapping("article/{id}")
    public String readArticle(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.readArticle(id));
        return "article/read";
    }

    @GetMapping("article/{id}/edit")
    public String editArticle(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.readArticle(id));
        return "article/edit";
    }

    @PostMapping("article/{id}/update")
    public String updateArticle(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        articleService.update(id, new ArticleDto(title, content, password));
        return String.format("redirect:/article/%d", id);
    }

/*    // 삭제 페이지 이동
        //article 상세 페이지에서 바로 삭제로 단순화
    @PostMapping("article/delete")
    public String articlePassword(
            Long id,
            Long boardsId,
            Model model) {
        model.addAttribute("articleId", id);
        model.addAttribute("boardsId", boardsId);
        return "article/deleteArticle";
    }*/

    //비밀번호 확인 후 삭제
    @PostMapping("article/{id}/delete")
    public String deleteArticle(
            @PathVariable("id")
            Long id,
            @RequestParam("password")
            String password
    ) {
        // 비밀번호 확인 후 삭제
        articleService.delete(id, password);
        return "redirect:/board/{boardId}";
    }
}