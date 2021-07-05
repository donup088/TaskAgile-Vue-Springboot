package com.taskagile.controller.me;

import com.taskagile.controller.me.dto.MeResponse;
import com.taskagile.domain.board.Board;
import com.taskagile.domain.team.Team;
import com.taskagile.securiy.userdetails.CurrentUser;
import com.taskagile.securiy.userdetails.CustomUserDetails;
import com.taskagile.service.me.MeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/me")
public class MeController {
    private final MeService meService;

    @GetMapping("/board")
    public MeResponse.TeamAndBoard getBoardAndTeam(@CurrentUser CustomUserDetails customUserDetails) {
        List<Board> board = meService.getBoard(customUserDetails.getUser());
        List<Team> team = meService.getTeam(customUserDetails.getUser());

        return MeResponse.TeamAndBoard.build(board, team, customUserDetails.getUser());
    }
}
