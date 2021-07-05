package com.taskagile.controller.team.dto;

import lombok.*;

public class TeamResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetId {
        private Long teamId;

        public static TeamResponse.GetId build(Long teamId) {
            return TeamResponse.GetId.builder().teamId(teamId).build();
        }
    }
}
