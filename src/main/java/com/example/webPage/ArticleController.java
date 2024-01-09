package com.example.webPage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping
    public String article(Model model){
        model.addAttribute("articles", articleService.readAll());
                return "article/articleList";
    }

    @GetMapping("write")
    public String articleWrite(){
        return "article/write";
    }

}
