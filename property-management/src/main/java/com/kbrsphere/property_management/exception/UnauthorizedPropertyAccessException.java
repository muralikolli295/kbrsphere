package com.kbrsphere.property_management.exception;

public class UnauthorizedPropertyAccessException extends RuntimeException {

    public UnauthorizedPropertyAccessException(String message) {
        super(message);
    }
}