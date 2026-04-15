package com.kbrsphere.user_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePasswordDTO {

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(min = 6, message = "new password must be at least 6 characters")
    private String newPassword;

    public UpdatePasswordDTO() {
    }

    public UpdatePasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}