package com.kbrsphere.user_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDTO {

    @NotBlank(message = "userName is required")
    private String userName;

    @NotBlank(message = "userEmail is required")
    @Email(message = "Invalid email format")
    private String userEmail;

    @NotBlank(message = "userPassword is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String userPassword;

    @NotNull(message = "phoneNumber is required")
    private Long phoneNumber;

    @NotNull(message = "role is required")
    private Role role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
