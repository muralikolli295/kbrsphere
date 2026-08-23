package com.kbrsphere.user_management.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "userEmail is required")
    private String userEmail;

    @NotBlank(message = "password is required")
    private String userPassword;

    public LoginRequestDTO(String userEmail, String password) {
        this.userEmail = userEmail;
        this.userPassword = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}