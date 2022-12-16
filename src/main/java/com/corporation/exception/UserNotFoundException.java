package com.corporation.exception;

/**
 * @author Bleschunov Dmitry
 */
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
