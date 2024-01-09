package com.example.webPage;

import com.example.webPage.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    @PostMapping("{commentId}/delete")
    public String delete(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("commentId")
            Long commentId
    ) {
        commentService.delete(commentId);
        return String.format("redirect:/article/%d", articleId);
    }
}
