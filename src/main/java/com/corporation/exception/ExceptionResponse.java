package com.corporation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private int status;
    private String message;
}
