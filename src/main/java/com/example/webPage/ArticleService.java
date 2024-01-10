package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import com.example.webPage.entity.Article;
import com.example.webPage.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    //CREATE
    public ArticleDto create(ArticleDto dto){
        Article article = new Article(
                dto.getArticleId(), dto.getTitle(), dto.getContent(), dto.getPassword()
        );
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public List<ArticleDto> readAll(){
        List<ArticleDto> articlelist = new ArrayList<>();
        for(Article article:articleRepository.findAll()){
            articlelist.add(ArticleDto.fromEntity(article));
        }
        return articlelist;
    }

    public ArticleDto read(Long id){
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
//    public boolean checkPassword(Long articleId, String enteredPassword) {
//        Optional<Article> optionalArticle = articleRepository.findById(articleId);
//        if (optionalArticle.isPresent()) {
//            Article article = optionalArticle.get();
//            System.out.println("Article found: " + article);
//            return article.getPassword().equals(enteredPassword);
//        } else {
//            System.out.println("Article not found for id: " + articleId);
//            return false;
//        }
//    }

    public void delete(Long id, String password) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            if (article.getPassword().equals(password)) {
                articleRepository.delete(article);
            }
        }
    }


}