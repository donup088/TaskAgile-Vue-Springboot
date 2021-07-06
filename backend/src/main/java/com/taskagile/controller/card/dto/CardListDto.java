package com.taskagile.controller.card.dto;

import com.taskagile.domain.card.CardList;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class CardListDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetList {
        private Long id;
        private String name;
        private Integer position;
        private List<CardDto.GetCard> cards;

        public static GetList build(CardList cardList){
            return GetList.builder()
                    .id(cardList.getId())
                    .name(cardList.getName())
                    .position(cardList.getPosition())
                    .cards(cardList.getCards().stream().map(CardDto.GetCard::build).collect(Collectors.toList()))
                    .build();
        }
    }
}
