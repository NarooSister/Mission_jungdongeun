package com.example.webPage;

import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.function.array.ArrayToStringFunction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    //CREATE
    public ArticleDto create(ArticleDto dto){
        Article article = new Article(
                dto.getTitle(), dto.getContent(), dto.getPassword()
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

    //수정할 때, 비밀번호는 수정 불가함
    public ArticleDto update(Long id, ArticleDto dto){
        Article article = articleRepository.findById(id).orElseThrow();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public void delete(Long id){
        articleRepository.delete(articleRepository.findById(id).orElseThrow());
    }
}
