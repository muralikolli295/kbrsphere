package com.kbrsphere.user_management.dto;

public class ApiResponse<T> {

    private ApiStatus status;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(ApiStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}