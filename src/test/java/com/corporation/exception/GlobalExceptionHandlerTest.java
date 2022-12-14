package com.corporation.exception;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class GlobalExceptionHandlerTest {

    @Spy
    private MessageSource messageSource;

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    public void shouldReturnExceptionResponseAndStatus400() {
        String title = "java";
        NotUniqueSkillException exception = new NotUniqueSkillException(title);
        ExceptionResponse exceptionResponse =
                exceptionHandler.handleNotUniqueSkillException(
                        exception,
                        Locale.ENGLISH
                );
        assertExceptionResponse(exceptionResponse, 400, title);
    }

    @Test
    public void shouldReturnExceptionResponseAndStatus404() {
        int id = 100;
        UserNotFoundException exception = new UserNotFoundException(id);
        ExceptionResponse exceptionResponse =
                exceptionHandler.handleUserNotFoundException(
                        exception,
                        Locale.ENGLISH
                );
        assertExceptionResponse(exceptionResponse, 404, id);
    }

    public void assertExceptionResponse(
            ExceptionResponse exceptionResponse,
            int expectedStatus,
            Object... messageParams
    ) {

        String message = messageSource.getMessage(
                "exception.not_unique_skill",
                messageParams,
                Locale.ENGLISH
        );

        Assertions.assertEquals(expectedStatus, exceptionResponse.getStatus());
        Assertions.assertEquals(message, exceptionResponse.getMessage());
    }
}
