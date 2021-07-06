package com.taskagile.service.board;

import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.domain.board.Board;
import com.taskagile.domain.board.BoardRepository;
import com.taskagile.domain.team.Team;
import com.taskagile.domain.team.TeamRepository;
import com.taskagile.domain.user.User;
import com.taskagile.service.board.exception.BoardNotFoundException;
import com.taskagile.service.team.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;

    public Board create(BoardRequest.create request, User user) {
        if (request.getTeamId() == 0) {
            Board board = Board.create(request, null, user);
            return boardRepository.save(board);
        }
        Team team = teamRepository.findById(request.getTeamId()).orElseThrow(TeamNotFoundException::new);
        Board board = Board.create(request, team, user);
        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }
}
