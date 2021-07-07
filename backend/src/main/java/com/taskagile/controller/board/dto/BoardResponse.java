package com.taskagile.controller.board.dto;

import com.taskagile.controller.card.dto.CardListDto;
import com.taskagile.controller.team.dto.TeamDto;
import com.taskagile.controller.user.dto.UserDto;
import com.taskagile.domain.board.Board;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class BoardResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetBoard {
        private TeamDto.GetOne team;
        private BoardDto.GetOneWithPersonal board;
        private List<UserDto.GetIdAndName> members;
        private List<CardListDto.GetList> cardLists;

        public static BoardResponse.GetBoard build(Board board){
            return GetBoard.builder()
                    .team(TeamDto.GetOne.build(board.getTeam()))
                    .board(BoardDto.GetOneWithPersonal.build(board))
                    .members(board.getBoardUsers().stream().map(boardUser -> UserDto.GetIdAndName.build(boardUser.getUser())).collect(Collectors.toList()))
                    .cardLists(board.getCardLists().stream().map(CardListDto.GetList::build).collect(Collectors.toList()))
                    .build();
        }
    }
}
