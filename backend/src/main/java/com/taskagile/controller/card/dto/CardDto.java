package com.taskagile.controller.card.dto;

import com.taskagile.domain.card.Card;
import lombok.*;

public class CardDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetCard {
        private Long id;
        private String title;
        private Integer position;

        public static CardDto.GetCard build(Card card) {
            return GetCard.builder().id(card.getId()).title(card.getTitle()).position(card.getPosition()).build();
        }
    }
}
