package com.kbrsphere.user_management.dto;

public class UpdateUserDTO {

    private String userName;
    private Long phoneNumber;

    public UpdateUserDTO() {
    }

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