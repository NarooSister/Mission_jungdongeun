package com.example.webPage;

import com.example.webPage.dto.CommentDto;
import com.example.webPage.entity.Article;
import com.example.webPage.entity.Comment;
import com.example.webPage.repository.ArticleRepository;
import com.example.webPage.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    //CREATE
    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow();
        Comment comment = new Comment(
                dto.getContent(), dto.getPassword(), article
        );
        return CommentDto.fromEntity(commentRepository.save(comment));
    }
    //READ
    public CommentDto read(Long commentId) {
        return CommentDto.fromEntity(commentRepository.findById(commentId)
                .orElseThrow());
    }

    //DELETE
    public void delete(Long commentId) {
        commentRepository.delete(commentRepository.findById(commentId)
                .orElseThrow());
    }
}