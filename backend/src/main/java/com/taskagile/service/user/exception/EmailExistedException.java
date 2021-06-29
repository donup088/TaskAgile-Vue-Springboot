package com.taskagile.service.user.exception;

import com.taskagile.exception.BusinessException;

public class EmailExistedException extends BusinessException {
    public EmailExistedException() {
        super("이미 존재하는 이메일입니다.");
    }
}
