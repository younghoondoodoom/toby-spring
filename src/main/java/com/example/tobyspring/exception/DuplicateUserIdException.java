package com.example.tobyspring.exception;

public class DuplicateUserIdException extends RuntimeException {

    public DuplicateUserIdException(Throwable cause) {
        super(cause);
    }
}
