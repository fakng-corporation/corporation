package com.corporation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@RequiredArgsConstructor
public class ExceptionResponse {
    private final String message;
}
