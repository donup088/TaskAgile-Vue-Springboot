package com.taskagile.controller.me.dto;

import com.taskagile.controller.board.dto.BoardDto;
import com.taskagile.controller.team.dto.TeamDto;
import com.taskagile.controller.user.dto.UserDto;
import com.taskagile.domain.board.Board;
import com.taskagile.domain.team.Team;
import com.taskagile.domain.user.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class MeResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TeamAndBoard {
        private List<BoardDto.Board> boards;
        private List<TeamDto.Team> teams;
        private UserDto.User user;

        public static MeResponse.TeamAndBoard build(List<Board> boards, List<Team> teams, User user) {
            return TeamAndBoard.builder()
                    .boards(boards.stream()
                            .map(board -> {
                                BoardDto.Board.BoardBuilder builder = BoardDto.Board.builder().id(board.getId()).name(board.getName()).description(board.getDescription());
                                if (board.getTeam() == null) {
                                    return builder.teamId(0L).build();
                                }
                                return builder.teamId(board.getTeam().getId()).build();
                            })
                            .collect(Collectors.toList()))
                    .teams(teams.stream()
                            .map(team -> TeamDto.Team.builder().id(team.getId()).name(team.getName()).build())
                            .collect(Collectors.toList()))
                    .user(UserDto.User.builder().name(user.getName()).build())
                    .build();
        }
    }
}
