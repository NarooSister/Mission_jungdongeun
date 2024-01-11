package com.example.webPage;

import com.example.webPage.dto.ArticleDto;
import com.example.webPage.dto.BoardsDto;
import com.example.webPage.entity.Article;
import com.example.webPage.entity.Boards;
import com.example.webPage.repository.ArticleRepository;
import com.example.webPage.repository.BoardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final   ArticleRepository articleRepository;
    public List<BoardsDto> readAll() {
        List<BoardsDto> boardsList = new ArrayList<>();
        for (Boards boards : boardsRepository.findAll()) {
            boardsList.add(BoardsDto.fromEntity(boards));
        }
        return boardsList;
    }

    public BoardsDto read(Long id) {
        Boards boards = boardsRepository.findById(id).orElseThrow();
        return BoardsDto.fromEntity(boards);
    }

    // 내림차순 정렬
        public List<ArticleDto> findByBoardsId(Long id) {
        List<ArticleDto> articleList = new ArrayList<>();

        for (Article article : articleRepository.findByBoardsId(id, Sort.by(Sort.Direction.DESC, "id"))) {
            articleList.add(ArticleDto.fromEntity(article));
        }
        return articleList;
    }


}