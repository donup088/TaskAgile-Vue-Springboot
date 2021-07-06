package com.taskagile.controller.card.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CardRequest {
    @Getter
    public static class CreateCardList {
        @NotEmpty
        private String name;
        @NotNull
        private Integer position;
        @NotNull
        private Long boardId;
    }

    @Getter
    public static class CreateCard {
        @NotEmpty
        private String title;
        @NotEmpty
        private String description;
        @NotNull
        private Integer position;
        @NotNull
        private Long cardListId;
    }
}
