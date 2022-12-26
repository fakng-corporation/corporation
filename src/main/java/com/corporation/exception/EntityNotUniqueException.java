package com.corporation.exception;

/**
 * @author Bleschunov Dmitry
 */
public class EntityNotUniqueException extends BusinessException{
    public EntityNotUniqueException(String message) {
        super(message);
    }
}
