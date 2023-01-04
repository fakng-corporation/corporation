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

    @ExceptionHandler(NotUniqueEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleNotUniqueEntityException(
            NotUniqueEntityException exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(NotFoundEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundEntityException(
            NotFoundEntityException exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleBusinessException(
            BusinessException exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleUnknownException(
            Exception exception
    ) {
        return new ExceptionResponse(exception.getMessage());
    }
}
