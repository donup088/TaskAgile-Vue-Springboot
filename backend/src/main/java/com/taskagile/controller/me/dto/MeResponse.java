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
        private List<BoardDto.GetOne> boards;
        private List<TeamDto.GetOne> teams;
        private UserDto.GetIdAndName getIdAndName;

        public static MeResponse.TeamAndBoard build(List<Board> boards, List<Team> teams, User user) {
            return TeamAndBoard.builder()
                    .boards(boards.stream().map(BoardDto.GetOne::build).collect(Collectors.toList()))
                    .teams(teams.stream().map(TeamDto.GetOne::build).collect(Collectors.toList()))
                    .getIdAndName(UserDto.GetIdAndName.build(user))
                    .build();
        }
    }
}
