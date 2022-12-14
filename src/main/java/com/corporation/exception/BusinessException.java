package com.corporation.exception;

import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
public class BusinessException extends RuntimeException {
    private final Object[] params;

    public BusinessException(Object... params) {
        this.params = params;
    }
}
