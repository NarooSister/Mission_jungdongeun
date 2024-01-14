package com.example.webPage.dto;

import com.example.webPage.entity.Article;
import com.example.webPage.entity.Board;
import com.example.webPage.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String password;
    //id가 아니라 name 만 가져올 예정
    private String boardName;
    private final List<CommentDto> comments = new ArrayList<>();


    //password 포함한 생성자
    public ArticleDto(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;

    }

    //static factory method
    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent().replace("\n", "<br>");
        dto.boardName = entity.getBoard().getName();
        for (Comment comment: entity.getComments())
            dto.comments.add(CommentDto.fromEntity(comment));
        return dto;
    }

}