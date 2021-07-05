package com.taskagile.controller.board;

import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.controller.board.dto.BoardResponse;
import com.taskagile.domain.board.Board;
import com.taskagile.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/personal")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardResponse.GetId create(@RequestBody BoardRequest.create request) {
        Board board = boardService.createPersonal(request);
        return BoardResponse.GetId.build(board.getId());
    }
}
