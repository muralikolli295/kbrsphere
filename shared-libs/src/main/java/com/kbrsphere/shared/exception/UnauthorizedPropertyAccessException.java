package com.kbrsphere.shared.exception;

public class UnauthorizedPropertyAccessException extends RuntimeException {

    public UnauthorizedPropertyAccessException(String message) {
        super(message);
    }
}