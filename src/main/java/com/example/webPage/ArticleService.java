package com.example.webPage;

import lombok.RequiredArgsConstructor;
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
}
