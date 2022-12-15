package com.corporation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Bleschunov Dmitry
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotUniqueSkillException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleNotUniqueSkillException(
            NotUniqueSkillException exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserNotFoundException(
            UserNotFoundException exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBusinessException(
            BusinessException exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUnknownException(
            Exception exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }
}
