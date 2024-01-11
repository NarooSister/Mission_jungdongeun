package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import com.example.webPage.entity.Article;
import com.example.webPage.repository.ArticleRepository;
import com.example.webPage.repository.BoardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final BoardsRepository boardsRepository;
    private final ArticleRepository articleRepository;

    //CREATE
    public ArticleDto create(ArticleDto dto) {
        Article article = new Article(
                dto.getTitle(), dto.getContent(), dto.getPassword(), dto.getBoards()
        );
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public List<ArticleDto> readAll() {
        List<ArticleDto> articlelist = new ArrayList<>();

        for (Article article : articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))) {
            articlelist.add(ArticleDto.fromEntity(article));
        }
        return articlelist;
    }

    public ArticleDto read(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return ArticleDto.fromEntity(article);
    }

    //수정할 때, 비밀번호 확인 후 save
    public ArticleDto update(
            Long id,
            String password,
            ArticleDto dto) {
        Article article = articleRepository.findById(id).orElseThrow();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        if (article.getPassword().equals(password)) {
            return ArticleDto.fromEntity(articleRepository.save(article));
        }
        return dto;
    }

    //비밀번호 확인 메서드

    public void delete(Long id, String password) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            if (article.getPassword().equals(password)) {
                articleRepository.delete(article);
            }
        }
    }


    public List<ArticleDto> findByBoardsId(Long boardsId) {
        List<ArticleDto> articleList = new ArrayList<>();

        for (Article article : articleRepository.findByBoardsId(boardsId, Sort.by(Sort.Direction.DESC, "id"))) {
            articleList.add(ArticleDto.fromEntity(article));
        }
        return articleList;
    }
}
