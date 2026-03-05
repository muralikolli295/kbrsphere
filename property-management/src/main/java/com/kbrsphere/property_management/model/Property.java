package com.kbrsphere.property_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "properties")
public class Property {

    @Id
    private String propertyId;
    private String propertyTitle;
    private String city;
    private Double price;
    private String propertyType;
    private List<String> amenities;

    public Property() {
    }

    public Property(String propertyId, String propertyTitle, String city, Double price, String propertyType, List<String> eminities) {
        this.propertyId = propertyId;
        this.propertyTitle = propertyTitle;
        this.city = city;
        this.price = price;
        this.propertyType = propertyType;
        this.amenities = eminities;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
