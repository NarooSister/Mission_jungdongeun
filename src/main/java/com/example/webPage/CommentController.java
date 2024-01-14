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

    private final CommentService commentService;

    //CREATE
    @PostMapping
    public String createComment(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        commentService.createComment(articleId, new CommentDto(content, password));
        return String.format("redirect:/article/%d", articleId);
    }


    /*
    // 삭제 페이지 이동해서 삭제하기
    // 댓글 옆에서 비밀번호 입력 후 바로 삭제하는 것으로 단순화함
    @PostMapping("deleteComment")
    public String commentPassword(
            Long commentId,
            Long articleId,
            Model model) {

        model.addAttribute("commentId", commentId);
        model.addAttribute("articleId", articleId);
        return "article/deleteComment";
    }*/

    //비밀번호 확인 후 삭제
    @PostMapping("{commentId}/delete")
    public String commentDelete(
            @PathVariable("commentId") Long commentId,
            @PathVariable("articleId") Long articleId,
            @RequestParam("password") String password
    ) {
        // 비밀번호 확인 후 삭제
        commentService.deleteComment(commentId, password);
        return String.format("redirect:/article/%d", articleId);
    }

}