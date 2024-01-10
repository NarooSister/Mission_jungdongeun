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
public class BoardsDto {
    private Long boardsId;
    private String name;
    private List<ArticleDto> articles = new ArrayList<>();

    public BoardsDto(String name){
        this.name = name;
    }
    public static BoardsDto fromEntity(Boards entity) {
        BoardsDto dto = new BoardsDto();
        dto.boardsId = entity.getBoardsId();
        dto.name = entity.getName();
        for (Article articles: entity.getArticles())
            dto.articles.add(ArticleDto.fromEntity(articles));
        return dto;
    }

}
