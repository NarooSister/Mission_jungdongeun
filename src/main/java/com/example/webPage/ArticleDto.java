package com.example.webPage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleDto {
    private Long articleId;
    private String title;
    private String content;
    private String password;

    //title과 content만 필요한 경우의 생성자
    public ArticleDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    //password 포함한 생성자
    public ArticleDto(String title, String content, String password){
        this.title = title;
        this.content= content;
        this. password = password;
    }

    //static factory method
    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.articleId = entity.getArticleId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent().replace("\n", "<br>");
        dto.password = entity.getPassword();
        return dto;
    }
}
