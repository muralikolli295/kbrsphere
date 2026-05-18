package com.kbrsphere.email_service.dto;

import jakarta.validation.constraints.NotBlank;

public class EmailRequestDTO {

    @NotBlank(message = "to mail is required")
    private String to;

    @NotBlank(message = "subject is required")
    private String subject;

    @NotBlank(message = "name is required")
    private String name;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setBody(String name) {
        this.name = name;
    }
}
