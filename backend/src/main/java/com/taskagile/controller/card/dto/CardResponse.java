package com.taskagile.controller.card.dto;

import com.taskagile.domain.card.Card;
import com.taskagile.domain.card.CardList;
import lombok.*;

public class CardResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateCardList {
        private Long id;
        private String name;

        public static CreateCardList build(CardList cardList) {
            return CreateCardList.builder().id(cardList.getId()).name(cardList.getName()).build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateCard {
        private Long id;
        private String title;
        private Integer position;

        public static CardResponse.CreateCard build(Card card) {
            return CreateCard.builder().id(card.getId()).title(card.getTitle()).position(card.getPosition()).build();
        }
    }


}
