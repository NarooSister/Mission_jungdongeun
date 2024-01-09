package com.example.webPage;

import com.example.webPage.dto.BoardsDto;
import com.example.webPage.entity.Boards;
import com.example.webPage.repository.BoardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardsService {
    private final BoardsRepository boardsRepository;
    //READ
//    public List<BoardsDto> readAll(){
//        List <BoardsDto> boardsList = new ArrayList<>();
//        for(Boards boards:boardsRepository.findAll()){
//            boardsList.add(BoardsDto.fromEntity(boards));
//        }
//        return boardsList;
//    }
    public String getBoardNameById(Long boardId) {
        Optional<Boards> boardOptional = boardsRepository.findById(boardId);
        return boardOptional.map(Boards::getName).orElse("Unknown");
    }
}
