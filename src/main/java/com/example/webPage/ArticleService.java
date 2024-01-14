package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import com.example.webPage.entity.Article;
import com.example.webPage.entity.Board;
import com.example.webPage.repository.ArticleRepository;
import com.example.webPage.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;

    //CREATE
    public ArticleDto create(Long boardId, ArticleDto dto) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Article article = new Article();
        article.setBoard(board);
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setPassword(dto.getPassword());
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public List<ArticleDto> readAll() {
        List<ArticleDto> articlelist = new ArrayList<>();

        for (Article article : articleRepository.findAll()) {
            articlelist.add(ArticleDto.fromEntity(article));
        }
        return articlelist;
    }

    public ArticleDto readArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return ArticleDto.fromEntity(article);
    }

    //수정할 때, 비밀번호 확인 후 save
    public ArticleDto update(
            Long id,
            ArticleDto dto) {
        Article article = articleRepository.findById(id).orElseThrow();
        if (article.getPassword().equals(dto.getPassword())) {
            article.setTitle(dto.getTitle());
            article.setContent(dto.getContent());
        }
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public void delete(Long id, String password) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (article.getPassword().equals(password)) {
            articleRepository.delete(article);
        }
    }
}
