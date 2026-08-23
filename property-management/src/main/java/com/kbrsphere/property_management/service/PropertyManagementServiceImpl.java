package com.kbrsphere.property_management.service;

import com.kbrsphere.property_management.dto.PropertyRequestDTO;
import com.kbrsphere.property_management.dto.PropertyResponseDTO;
import com.kbrsphere.property_management.dto.PropertyStatus;
import com.kbrsphere.property_management.dto.StatusUpdateRequestDTO;
import com.kbrsphere.shared.exception.PropertyNotFoundException;
import com.kbrsphere.shared.exception.UnauthorizedPropertyAccessException;
import com.kbrsphere.property_management.model.Property;
import com.kbrsphere.property_management.repository.PropertyRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyManagementServiceImpl implements PropertyManagementService {

    private final PropertyRepository propertyRepository;

    public PropertyManagementServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Long.valueOf(authentication.getName());
    }

    @Override
    public PropertyResponseDTO registerProperty(PropertyRequestDTO request) {
        Long ownerId = getCurrentUserId();

        Property property = new Property();
        property.setPropertyTitle(request.getPropertyTitle());
        property.setCity(request.getCity());
        property.setPrice(request.getPrice());
        property.setPropertyType(request.getPropertyType());
        property.setAmenities(request.getAmenities());
        property.setOwnerId(ownerId); // Owner comes from JWT
        property.setStatus(PropertyStatus.AVAILABLE);  // New properties are available by default
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());

        Property savedProperty = propertyRepository.save(property);

        return convertToResponse(savedProperty);
    }

    @Override
    public List<PropertyResponseDTO> getProperties() {
        return propertyRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }


    @Override
    public PropertyResponseDTO getProperty(String propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFoundException("Property not found with ID: " + propertyId));

        return convertToResponse(property);
    }

    @Override
    public PropertyResponseDTO updateProperty(String propertyId, PropertyRequestDTO request) {
        Long currentUserId = getCurrentUserId();

        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFoundException("Property not found with ID: " + propertyId));

        if (!property.getOwnerId().equals(currentUserId)) {
            throw new UnauthorizedPropertyAccessException("You are not authorized to update this property");
        }

        property.setPropertyTitle(request.getPropertyTitle());
        property.setCity(request.getCity());
        property.setPrice(request.getPrice());
        property.setPropertyType(request.getPropertyType());
        property.setAmenities(request.getAmenities());
        property.setUpdatedAt(LocalDateTime.now());

        Property updatedProperty = propertyRepository.save(property);

        return convertToResponse(updatedProperty);
    }

    @Override
    public void deleteProperty(String propertyId) {
        Long currentUserId = getCurrentUserId();

        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFoundException("Property not found with ID: " + propertyId));
        if (!property.getOwnerId().equals(currentUserId)) {
            throw new UnauthorizedPropertyAccessException("You are not authorized to delete this property");
        }

        propertyRepository.delete(property);
    }

    @Override
    public List<PropertyResponseDTO> getMyProperties() {
        Long currentUserId = getCurrentUserId();

        return propertyRepository.findByOwnerId(currentUserId).stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public PropertyResponseDTO updatePropertyStatus(String propertyId, StatusUpdateRequestDTO request) {
        Long currentUserId = getCurrentUserId();
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFoundException("Property not found with ID: " + propertyId));

        if (!property.getOwnerId().equals(currentUserId)) {
            throw new UnauthorizedPropertyAccessException("You are not authorized to update this property");
        }

        property.setStatus(request.getStatus());
        property.setUpdatedAt(LocalDateTime.now());

        return convertToResponse(propertyRepository.save(property));
    }

    @Override
    public List<PropertyResponseDTO> searchProperties(String city, String propertyType, Double minPrice, Double maxPrice, PropertyStatus status) {
        List<Property> properties = propertyRepository.findAll();

        return properties.stream().filter(property -> city == null || property.getCity().equalsIgnoreCase(city)).filter(property -> propertyType == null || property.getPropertyType().equalsIgnoreCase(propertyType)).filter(property -> minPrice == null || property.getPrice() >= minPrice).filter(property -> maxPrice == null || property.getPrice() <= maxPrice).filter(property -> status == null || property.getStatus() == status).map(this::convertToResponse).collect(Collectors.toList());
    }

    private PropertyResponseDTO convertToResponse(Property property) {
        PropertyResponseDTO response = new PropertyResponseDTO();

        response.setPropertyId(property.getPropertyId());
        response.setPropertyTitle(property.getPropertyTitle());
        response.setCity(property.getCity());
        response.setPrice(property.getPrice());
        response.setPropertyType(property.getPropertyType());
        response.setAmenities(property.getAmenities());
        response.setOwnerId(property.getOwnerId());
        response.setStatus(property.getStatus());
        response.setCreatedAt(property.getCreatedAt());
        response.setUpdatedAt(property.getUpdatedAt());

        return response;
    }
}