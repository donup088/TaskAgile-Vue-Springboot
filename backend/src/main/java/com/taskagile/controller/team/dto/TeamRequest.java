package com.taskagile.controller.team.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

public class TeamRequest {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class create {
        @NotEmpty
        private String name;
    }
}
