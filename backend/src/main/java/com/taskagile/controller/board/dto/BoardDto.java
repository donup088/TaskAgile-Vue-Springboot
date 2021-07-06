package com.taskagile.controller.board.dto;

import com.taskagile.domain.board.Board;
import lombok.*;

public class BoardDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetBoard {
        private Long id;
        private String name;
        private String description;
        private Long teamId;

        public static GetBoard build(Board board) {
            GetBoardBuilder builder = GetBoard.builder().id(board.getId()).name(board.getName()).description(board.getDescription());
            if (board.getTeam() == null) {
                return builder.teamId(0L).build();
            }
            return builder.teamId(board.getTeam().getId()).build();
        }
    }
}
