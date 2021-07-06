package com.taskagile.controller.board;

import com.taskagile.controller.board.dto.BoardDto;
import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.domain.board.Board;
import com.taskagile.securiy.userdetails.CurrentUser;
import com.taskagile.securiy.userdetails.CustomUserDetails;
import com.taskagile.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto.GetBoard create(@RequestBody BoardRequest.create request, @CurrentUser CustomUserDetails customUserDetails) {
        Board board = boardService.create(request, customUserDetails.getUser());

        return BoardDto.GetBoard.build(board);
    }
}
