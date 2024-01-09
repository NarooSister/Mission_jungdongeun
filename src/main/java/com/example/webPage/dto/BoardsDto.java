package com.example.webPage.dto;

import com.example.webPage.entity.Article;
import com.example.webPage.entity.Boards;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardsDto {
    private Long boardsId;
    private String name;
    private List<ArticleDto> articles = new ArrayList<>();

    public BoardsDto(String name){
        this.name = name;
    }

    //static factory method
    public static BoardsDto fromEntity(Boards entity){
        BoardsDto dto = new BoardsDto();
        dto.boardsId = entity.getBoardsId();
        dto.name = entity.getName();
        dto.articles = new ArrayList<>();
        for(Article article: entity.getArticles())
            dto.articles.add(ArticleDto.fromEntity(article));
        return dto;
    }

}

