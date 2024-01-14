package com.example.webPage.dto;

import com.example.webPage.entity.Article;
import com.example.webPage.entity.Board;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    @Setter
    private String name;
    private final List<ArticleDto> articles = new ArrayList<>();

    public BoardDto(String name){
        this.name = name;
    }
    public static BoardDto fromEntity(Board entity) {
        BoardDto dto = new BoardDto();
        dto.id = entity.getId();
        dto.name = entity.getName();
        for (Article articles: entity.getArticles())
            dto.articles.add(ArticleDto.fromEntity(articles));
        return dto;
    }

}
