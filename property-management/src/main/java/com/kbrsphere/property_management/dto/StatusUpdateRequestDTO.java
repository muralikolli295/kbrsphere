package com.kbrsphere.property_management.dto;

import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequestDTO {

    @NotNull(message = "Property status is required")
    private PropertyStatus status;

    public StatusUpdateRequestDTO() {
    }

    public StatusUpdateRequestDTO(PropertyStatus status) {
        this.status = status;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }
}