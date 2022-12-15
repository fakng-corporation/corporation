package com.corporation.exception;

public class NotUniquePostException extends RuntimeException{

    public NotUniquePostException(String postTitle) {
        super(String.format("Post with title %s has already exists!", postTitle));
    }

}
