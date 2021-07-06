package com.taskagile.controller.board.dto;

import com.taskagile.domain.board.Board;
import lombok.*;

public class BoardDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetOne {
        private Long id;
        private String name;
        private String description;
        private Long teamId;

        public static GetOne build(Board board) {
            GetOneBuilder builder = GetOne.builder().id(board.getId()).name(board.getName()).description(board.getDescription());
            if (board.getTeam() == null) {
                return builder.teamId(0L).build();
            }
            return builder.teamId(board.getTeam().getId()).build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetOneWithPersonal {
        private Long id;
        private String name;
        private Boolean personal;

        public static GetOneWithPersonal build(Board board) {
            GetOneWithPersonalBuilder builder = GetOneWithPersonal.builder().id(board.getId()).name(board.getName());
            if (board.getTeam() == null) {
                return builder.personal(true).build();
            }
            return builder.personal(false).build();
        }
    }
}
