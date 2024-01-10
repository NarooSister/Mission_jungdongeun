package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import com.example.webPage.dto.BoardsDto;
import com.example.webPage.entity.Article;
import com.example.webPage.entity.Boards;
import com.example.webPage.repository.ArticleRepository;
import com.example.webPage.repository.BoardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardsService {
    private final BoardsRepository boardsRepository;

//    //CREATE
//    public ArticleDto create(ArticleDto dto){
//        Article article = new Article(
//                dto.getArticleId(), dto.getTitle(), dto.getContent(), dto.getPassword()
//        );
//        return ArticleDto.fromEntity(articleRepository.save(article));
//    }
//
    public List<BoardsDto> readAll(){
        List<BoardsDto> boardsList = new ArrayList<>();
        for(Boards boards: boardsRepository.findAll()){
            boardsList.add(BoardsDto.fromEntity(boards));
        }
        return boardsList;
    }

    public BoardsDto read(Long id){
        Boards boards = boardsRepository.findById(id).orElseThrow();
        return BoardsDto.fromEntity(boards);
    }
//
//    //수정할 때, 비밀번호는 수정 불가함
//    public ArticleDto update(Long id, ArticleDto dto){
//        Article article = articleRepository.findById(id).orElseThrow();
//        article.setTitle(dto.getTitle());
//        article.setContent(dto.getContent());
//        return ArticleDto.fromEntity(articleRepository.save(article));
//    }
//
//    public void delete(Long id){
//        articleRepository.delete(articleRepository.findById(id).orElseThrow());
//    }
}