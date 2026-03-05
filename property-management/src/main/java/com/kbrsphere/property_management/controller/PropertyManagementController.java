package com.kbrsphere.property_management.controller;

import com.kbrsphere.property_management.model.Property;
import com.kbrsphere.property_management.service.PropertyManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyManagementController {

    @Autowired
    private PropertyManagementService managementService;

    @PostMapping("/registerProperty")
    private String registerProperty(@RequestBody Property property){
       return managementService.registerProperty(property);
    }

    @GetMapping("/propertyList")
    public List<Property> getProperties(){
        return managementService.getProperties();
    }

}
