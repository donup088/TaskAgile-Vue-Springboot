package com.taskagile.service.board;

import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.domain.board.Board;
import com.taskagile.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board createPersonal(BoardRequest.create request){
        Board board = Board.createPersonal(request);
        return boardRepository.save(board);
    }
}
