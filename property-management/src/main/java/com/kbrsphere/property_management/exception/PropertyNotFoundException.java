package com.kbrsphere.property_management.exception;

public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException(String message) {
        super(message);
    }
}