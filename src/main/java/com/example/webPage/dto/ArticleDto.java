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
    private Long id;
    private String title;
    private String content;
    private String password;
    private Long boardsId; // 추가: boardsId 필드
    private final List<CommentDto> comments = new ArrayList<>();

    private Boards boards;


    //title과 content만 필요한 경우의 생성자
    public ArticleDto(String title, String content){
        this.title = title;
        this.content = content;
    }
    //password 포함한 생성자
    public ArticleDto(String title, String content, String password, Long boardsId) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.boardsId = boardsId;
    }

    //static factory method
    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent().replace("\n", "<br>");
        dto.password = entity.getPassword();
        dto.boards = entity.getBoards();
        dto.boardsId = entity.getBoards().getId(); // 추가: boardsId 설정
        for (Comment comment: entity.getComments())
            dto.comments.add(CommentDto.fromEntity(comment));
        return dto;
    }

}