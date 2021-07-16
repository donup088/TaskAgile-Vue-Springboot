package com.taskagile.service.user.exception;

import com.taskagile.exception.BusinessException;

public class RefreshTokenExpiredException extends BusinessException {
    public RefreshTokenExpiredException() {
        super("만료된 Refresh 토큰입니다.");
    }
}
