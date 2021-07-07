package com.taskagile.controller.board.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class BoardRequest {
    @Getter
    public static class create {
        @NotEmpty
        private String name;
        private String description;
        @NotEmpty
        private Long teamId;
    }

    @Getter
    public static class AddMember {
        @NotEmpty
        private String name;
    }
}
