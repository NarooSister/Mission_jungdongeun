package com.example.webPage;

import com.example.webPage.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("article/{articleId}/comment")
public class CommentController {
    private final ArticleService articleService;
    private final CommentService commentService;

    //CREATE
    @PostMapping
    public String create(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        commentService.create(articleId, new CommentDto(content, password));
        return String.format("redirect:/article/%d", articleId);
    }
    //DELETE
//    @PostMapping("{commentId}/delete")
//    public String delete(
//            @PathVariable("articleId")
//            Long articleId,
//            @PathVariable("commentId")
//            Long commentId
//    ) {
//        commentService.delete(commentId);
//        return String.format("redirect:/article/%d", articleId);
//    }

    // 삭제 페이지 이동
    @PostMapping("deleteComment")
    public String commentPassword(
            Long commentId,
            Long articleId,
            Model model) {

        model.addAttribute("commentId", commentId);
        model.addAttribute("articleId", articleId);
        return "article/deleteComment";
    }

    //비밀번호 확인 후 삭제
    @PostMapping("{commentId}/deleteComment")
    public String commentDelete(
            @PathVariable("commentId") Long commentId,
            @PathVariable("articleId") Long articleId,
            @RequestParam("password") String password
    ) {
        // 비밀번호 확인 후 삭제
        commentService.delete(commentId, password);
        return "redirect:/article/{articleId}";
    }

}