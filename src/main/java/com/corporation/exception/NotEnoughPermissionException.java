package com.corporation.exception;

public class NotEnoughPermissionException extends BusinessException{
    public NotEnoughPermissionException(String message) {
        super(message);
    }
}
