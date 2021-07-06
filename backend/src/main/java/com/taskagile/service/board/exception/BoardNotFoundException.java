package com.taskagile.service.board.exception;

import com.taskagile.exception.EntityNotFoundException;

public class BoardNotFoundException extends EntityNotFoundException {
    public BoardNotFoundException() {
        super("해당 보드가 존재하지 않습니다.");
    }
}
