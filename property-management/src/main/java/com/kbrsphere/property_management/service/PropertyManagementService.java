package com.kbrsphere.property_management.service;

import com.kbrsphere.property_management.model.Property;
import com.kbrsphere.property_management.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyManagementService {

    @Autowired
    private PropertyRepository propertyRepository;

    public String registerProperty(Property property){
        property.setPropertyId("PROP-" + System.currentTimeMillis());
        return String.valueOf(propertyRepository.save(property));
    }

    public List<Property> getProperties(){
        return propertyRepository.findAll();
    }

    public Optional<Property> getProperty(String propertyId) {
        return propertyRepository.findById(propertyId);
    }
}
