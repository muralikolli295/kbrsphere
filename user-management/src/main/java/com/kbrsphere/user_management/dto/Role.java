package com.kbrsphere.user_management.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN,
    OWNER,
    CUSTOMER;

    @JsonCreator
    public static Role from(String value) {
         return Role.valueOf(value.toUpperCase());
    }
}