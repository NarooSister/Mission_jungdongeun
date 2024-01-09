package com.example.webPage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    //CREATE
    //http post요청이 /article 경로로 들어왔을 때 이 메서드가 처리
    @PostMapping
    public String articleCreate(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ){
        //받아온 데이터를 이용하여 ArticleDto을 생성하고
        // articleService.create 메서드를 이용해
        //실제로 새로운 게시글로 저장.
        //이렇게 생성된 게시글의 새 Id를 newId에 저장
        //성공적으로 생성되면 article/newId로 리다이렉트
        Long newId = articleService.create(new ArticleDto(title, content, password)).getId();
            return String.format("redirect:/article/%d", newId);
    }

    @GetMapping("{id}")
    public String articleOne(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article", articleService.read(id));
        return "article/read";
    }

}
