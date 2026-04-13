package com.kbrsphere.property_management.controller;

import com.kbrsphere.property_management.model.Property;
import com.kbrsphere.property_management.service.PropertyManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
public class PropertyManagementController {

    @Autowired
    private PropertyManagementService propertyService;

    @PostMapping("/property")
    private String registerProperty(@RequestBody Property property){
       return propertyService.registerProperty(property);
    }

    @GetMapping("/properties")
    public List<Property> getProperties(){
        return propertyService.getProperties();
    }

    @GetMapping("/property/{propertyId}")
    public Optional<Property> getProperty(@PathVariable String propertyId){
        return propertyService.getProperty(propertyId);
    }

}
