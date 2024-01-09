package com.example.webPage.dto;

import com.example.webPage.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private String content;
    private String password;
    public CommentDto(String content, String password){
        this.content = content;
        this.password = password;
    }
    public static CommentDto fromEntity(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.commentId = entity.getCommentId();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        return dto;
    }
}
