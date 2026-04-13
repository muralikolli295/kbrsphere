package com.kbrsphere.user_management.controller;

import com.kbrsphere.user_management.dto.ApiResponse;
import com.kbrsphere.user_management.dto.UserRequestDTO;
import com.kbrsphere.user_management.dto.UserResponseDTO;
import com.kbrsphere.user_management.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserManagementController {

    @Autowired
    private UserManagementService service;

    // REGISTER
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@RequestBody UserRequestDTO request) {
        return ResponseEntity.status(201)
                .body(new ApiResponse<>("success", "User registered successfully", service.register(request)));
    }

    // GET ALL USERS (ADMIN)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers() {
        return ResponseEntity.ok(
                new ApiResponse<>("success", "Users fetched successfully", service.getUsers()));
    }

    // GET USER BY ID (ADMIN OR SELF)
    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable String id) {
        return ResponseEntity.ok(
                new ApiResponse<>("success", "User fetched successfully", service.getUserById(id))
        );
    }
}