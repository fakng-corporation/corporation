package com.corporation.exception;

/**
 * @author Bleschunov Dmitry
 */
public class NotFoundEntityException extends BusinessException {
    public NotFoundEntityException(String message) {
        super(message);
    }
}
