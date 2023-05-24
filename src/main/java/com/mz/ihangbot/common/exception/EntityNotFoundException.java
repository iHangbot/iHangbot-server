package com.mz.ihangbot.common.exception;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}