package com.example.webPage.dto;

import com.example.webPage.entity.Article;
import com.example.webPage.entity.Boards;
import com.example.webPage.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ArticleDto {
    private Long articleId;
    private String title;
    private String content;
    private String password;
    private List<CommentDto> comments = new ArrayList<>();
    private Boards boards;


    //password 포함한 생성자
    public ArticleDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    //password 포함한 생성자
    public ArticleDto(String title, String content, String password, Boards boards){
        this.title = title;
        this.content= content;
        this. password = password;
        this.boards =boards;
    }

    //static factory method
    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.articleId = entity.getArticleId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent().replace("\n", "<br>");
        dto.password = entity.getPassword();
        dto.comments = new ArrayList<>();
        for (Comment comment: entity.getComments())
            dto.comments.add(CommentDto.fromEntity(comment));
        return dto;
    }
}