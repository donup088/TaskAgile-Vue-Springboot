package com.taskagile.service.user.exception;

import com.taskagile.exception.BusinessException;

public class PasswordWrongException extends BusinessException {
    public PasswordWrongException() {
        super("비밀번호가 잘못되었습니다.");
    }
}
