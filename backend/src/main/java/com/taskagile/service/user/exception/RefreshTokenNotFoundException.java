package com.taskagile.service.user.exception;

import com.taskagile.exception.EntityNotFoundException;

public class RefreshTokenNotFoundException extends EntityNotFoundException {
    public RefreshTokenNotFoundException() {
        super("RefreshToken이 존재하지 않습니다.");
    }
}
