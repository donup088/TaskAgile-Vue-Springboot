package com.taskagile.controller.board.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

public class BoardRequest {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class create {
        @NotEmpty
        private String name;

        private String description;
    }
}
