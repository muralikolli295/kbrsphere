package com.kbrsphere.property_management.dto;

import jakarta.validation.constraints.Positive;

import java.util.List;

public class PropertyPatchRequestDTO {

    private String propertyTitle;

    private String city;

    @Positive(message = "Price must be greater than zero")
    private Double price;

    private String propertyType;

    private List<String> amenities;

    public PropertyPatchRequestDTO() {
    }


    // Parameterized Constructor
    public PropertyPatchRequestDTO(String propertyTitle, String city, Double price, String propertyType, List<String> amenities) {

        this.propertyTitle = propertyTitle;
        this.city = city;
        this.price = price;
        this.propertyType = propertyType;
        this.amenities = amenities;
    }


    // Getters and Setters

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
}