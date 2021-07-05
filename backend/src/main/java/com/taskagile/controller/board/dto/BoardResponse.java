package com.taskagile.controller.board.dto;

import lombok.*;

public class BoardResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetId {
        private Long boardId;

        public static GetId build(Long boardId) {
            return GetId.builder().boardId(boardId).build();
        }
    }
}
