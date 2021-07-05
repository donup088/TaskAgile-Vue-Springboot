package com.taskagile.controller.board.dto;

import lombok.*;

public class BoardDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Board {
        private Long id;
        private String name;
        private String description;
        private Long teamId;
    }
}
