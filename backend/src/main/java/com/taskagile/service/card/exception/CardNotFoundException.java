package com.taskagile.service.card.exception;

import com.taskagile.exception.EntityNotFoundException;

public class CardNotFoundException extends EntityNotFoundException {
    public CardNotFoundException() {
        super("존재하지 않는 카드입니다.");
    }
}
