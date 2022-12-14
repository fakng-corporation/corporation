package com.corporation.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

/**
 * @author Bleschunov Dmitry
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(NotUniqueSkillException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleNotUniqueSkillException(
            NotUniqueSkillException exception,
            Locale locale
    ) {

        // do some logic here...

        return createExceptionResponse(
                400,
                "exception.not_unique_skill",
                locale,
                exception.getParams()
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserNotFoundException(
            UserNotFoundException exception,
            Locale locale
    ) {

        // do some logic here...

        return createExceptionResponse(
                404,
                "exception.user_not_found",
                locale,
                exception.getParams()
        );
    }

    private ExceptionResponse createExceptionResponse(
            int status,
            String message_title,
            Locale locale,
            Object... params
    ) {
        String message = messageSource.getMessage(message_title, params, locale);
        return new ExceptionResponse(status, message);
    }
}
