package com.kbrsphere.property_management.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PropertyResponseDTO {

    private String propertyId;
    private String propertyTitle;
    private String city;
    private Double price;
    private String propertyType;
    private List<String> amenities;
    private Long ownerId;
    private PropertyStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PropertyResponseDTO() {
    }

    public PropertyResponseDTO(String propertyId, String propertyTitle, String city, Double price, String propertyType, List<String> amenities, Long ownerId, PropertyStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.propertyId = propertyId;
        this.propertyTitle = propertyTitle;
        this.city = city;
        this.price = price;
        this.propertyType = propertyType;
        this.amenities = amenities;
        this.ownerId = ownerId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}