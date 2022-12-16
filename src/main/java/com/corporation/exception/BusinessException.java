package com.corporation.exception;

/**
 * @author Bleschunov Dmitry
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
