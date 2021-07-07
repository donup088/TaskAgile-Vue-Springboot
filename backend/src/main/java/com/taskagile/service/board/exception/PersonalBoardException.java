package com.taskagile.service.board.exception;

import com.taskagile.exception.BusinessException;

public class PersonalBoardException extends BusinessException {
    public PersonalBoardException() {
        super("PesonalBoard에 멤버를 추가할 수 없습니다.");
    }
}
