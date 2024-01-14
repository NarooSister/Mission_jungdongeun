package com.example.webPage;

import com.example.webPage.dto.CommentDto;
import com.example.webPage.entity.Article;
import com.example.webPage.entity.Comment;
import com.example.webPage.repository.ArticleRepository;
import com.example.webPage.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    //CREATE
    public CommentDto createComment(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setContent(dto.getContent());
        comment.setPassword(dto.getPassword());
        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    public void deleteComment(Long id, String password) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        if (comment.getPassword().equals(password)) {
                commentRepository.delete(comment);
        }
    }
}