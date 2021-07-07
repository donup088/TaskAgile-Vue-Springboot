package com.taskagile.service.board;

import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.domain.board.Board;
import com.taskagile.domain.board.BoardRepository;
import com.taskagile.domain.board.BoardUser;
import com.taskagile.domain.board.BoardUserRepository;
import com.taskagile.domain.team.Team;
import com.taskagile.domain.team.TeamRepository;
import com.taskagile.domain.team.TeamUser;
import com.taskagile.domain.team.TeamUserRepository;
import com.taskagile.domain.user.User;
import com.taskagile.domain.user.UserRepository;
import com.taskagile.service.board.exception.BoardNotFoundException;
import com.taskagile.service.board.exception.PersonalBoardException;
import com.taskagile.service.team.exception.TeamNotFoundException;
import com.taskagile.service.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardUserRepository boardUserRepository;
    private final TeamRepository teamRepository;
    private final TeamUserRepository teamUserRepository;
    private final UserRepository userRepository;

    public Board create(BoardRequest.create request, User user) {
        if (request.getTeamId() == 0) {
            Board board = Board.create(request, null, user);
            return boardRepository.save(board);
        }
        //TODO 로그인 유저가 TeamUser인지 체크해야함
        Team team = teamRepository.findById(request.getTeamId()).orElseThrow(TeamNotFoundException::new);
        Board board = Board.create(request, team, user);

        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    public BoardUser addMember(Long boardId, BoardRequest.AddMember request) {
        //TODO 로그인 유저가 해당 보드의 BoardUser 인지 체크해야함
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        Team team = board.getTeam();
        if (team == null) {
            throw new PersonalBoardException();
        }

        User user = userRepository.findByName(request.getName()).orElseThrow(UserNotFoundException::new);
        teamUserRepository.save(TeamUser.create(team, user));

        return boardUserRepository.save(BoardUser.create(board, user));
    }
}
