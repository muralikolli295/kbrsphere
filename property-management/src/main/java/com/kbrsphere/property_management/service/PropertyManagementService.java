package com.kbrsphere.property_management.service;

import com.kbrsphere.property_management.dto.PropertyRequestDTO;
import com.kbrsphere.property_management.dto.PropertyResponseDTO;
import com.kbrsphere.property_management.dto.PropertyStatus;
import com.kbrsphere.property_management.dto.StatusUpdateRequestDTO;

import java.util.List;

public interface PropertyManagementService {

    PropertyResponseDTO registerProperty(PropertyRequestDTO request);

    List<PropertyResponseDTO> getProperties();

    PropertyResponseDTO getProperty(String propertyId);

    PropertyResponseDTO updateProperty(String propertyId, PropertyRequestDTO request);

    void deleteProperty(String propertyId);

    List<PropertyResponseDTO> getMyProperties();

    PropertyResponseDTO updatePropertyStatus(String propertyId, StatusUpdateRequestDTO request);

    List<PropertyResponseDTO> searchProperties(String city, String propertyType, Double minPrice, Double maxPrice, PropertyStatus status);
}