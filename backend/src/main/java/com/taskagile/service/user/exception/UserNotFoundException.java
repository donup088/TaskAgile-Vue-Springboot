package com.taskagile.service.user.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("가입 되지 않은 이메일입니다.");
    }
}
