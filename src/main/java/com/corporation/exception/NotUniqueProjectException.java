package com.corporation.exception;

public class NotUniqueProjectException extends RuntimeException {
    public NotUniqueProjectException(String projectTitle) {
        super(String.format("A project with the title %s already exists.", projectTitle));
    }
}
