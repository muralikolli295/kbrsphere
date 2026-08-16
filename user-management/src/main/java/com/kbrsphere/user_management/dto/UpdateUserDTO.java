package com.kbrsphere.user_management.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateUserDTO {

    @NotNull()
    private String userName;
    private Long phoneNumber;

    public UpdateUserDTO(String userName, Long phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}