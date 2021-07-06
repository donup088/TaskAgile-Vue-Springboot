package com.taskagile.service.me;

import com.taskagile.domain.board.Board;
import com.taskagile.domain.board.BoardRepository;
import com.taskagile.domain.team.Team;
import com.taskagile.domain.team.TeamRepository;
import com.taskagile.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeService {
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;

    public List<Board> getBoard(User user){
        return boardRepository.findAllByUserInBoardUser(user);
    }

    public List<Team> getTeam(User user){
        return teamRepository.findAllByUser(user);
    }
}
