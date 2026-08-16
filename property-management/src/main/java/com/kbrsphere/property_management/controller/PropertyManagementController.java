package com.kbrsphere.property_management.controller;

import com.kbrsphere.property_management.dto.PropertyRequestDTO;
import com.kbrsphere.property_management.dto.PropertyResponseDTO;
import com.kbrsphere.property_management.dto.PropertyStatus;
import com.kbrsphere.property_management.dto.StatusUpdateRequestDTO;
import com.kbrsphere.property_management.service.PropertyManagementService;
import com.kbrsphere.shared.enums.ApiStatus;
import com.kbrsphere.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyManagementController {

    private final PropertyManagementService propertyService;

    public PropertyManagementController(PropertyManagementService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PropertyResponseDTO>> registerProperty(@Valid @RequestBody PropertyRequestDTO request) {
        PropertyResponseDTO response = propertyService.registerProperty(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ApiStatus.SUCCESS, "Property registered successfully", response));
    }

    @GetMapping("/properties")
    public ResponseEntity<ApiResponse<List<PropertyResponseDTO>>> getProperties() {

        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS, "Properties retrieved successfully", propertyService.getProperties()));
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<ApiResponse<PropertyResponseDTO>> getProperty(@PathVariable String propertyId) {

        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS, "Property retrieved successfully", propertyService.getProperty(propertyId)));
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<ApiResponse<PropertyResponseDTO>> updateProperty(@PathVariable String propertyId, @Valid @RequestBody PropertyRequestDTO request) {

        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS, "Property updated successfully", propertyService.updateProperty(propertyId, request)));
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<ApiResponse<Void>> deleteProperty(@PathVariable String propertyId) {
        propertyService.deleteProperty(propertyId);

        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS, "Property deleted successfully", null));
    }

    @GetMapping("/my-properties")
    public ResponseEntity<ApiResponse<List<PropertyResponseDTO>>> getMyProperties() {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS, "Owner properties retrieved successfully", propertyService.getMyProperties()));
    }

    @PatchMapping("/{propertyId}/status")
    public ResponseEntity<ApiResponse<PropertyResponseDTO>> updatePropertyStatus(@PathVariable String propertyId, @Valid @RequestBody StatusUpdateRequestDTO request) {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS, "Property status updated successfully", propertyService.updatePropertyStatus(propertyId, request)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PropertyResponseDTO>>> searchProperties(@RequestParam(required = false) String city, @RequestParam(required = false) String propertyType, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) PropertyStatus status) {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS, "Properties retrieved successfully", propertyService.searchProperties(city, propertyType, minPrice, maxPrice, status)));
    }


}