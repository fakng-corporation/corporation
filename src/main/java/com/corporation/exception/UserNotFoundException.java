package com.corporation.exception;

import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Object... params) {
        super(params);
    }
}
