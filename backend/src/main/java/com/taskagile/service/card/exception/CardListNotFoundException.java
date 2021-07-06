package com.taskagile.service.card.exception;

import com.taskagile.exception.EntityNotFoundException;

public class CardListNotFoundException extends EntityNotFoundException {
    public CardListNotFoundException() {
        super("존재하지 않는 카드 리스트 입니다.");
    }
}
