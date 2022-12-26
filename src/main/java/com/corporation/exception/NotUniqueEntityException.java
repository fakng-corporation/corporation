package com.corporation.exception;

public class NotUniqueEntityException extends BusinessException{
    public NotUniqueEntityException(String message) {
        super(message);
    }
}
