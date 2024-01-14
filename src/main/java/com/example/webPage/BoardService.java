package com.example.webPage;

import com.example.webPage.dto.BoardDto;
import com.example.webPage.entity.Board;
import com.example.webPage.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDto> readAll() {
        List<BoardDto> boardList = new ArrayList<>();
        for (Board boards : boardRepository.findAll()) {
            boardList.add(BoardDto.fromEntity(boards));
        }
        return boardList;
    }

    public BoardDto read(Long id) {
        Board boards = boardRepository.findById(id).orElseThrow();
        return BoardDto.fromEntity(boards);
    }
}