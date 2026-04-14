package com.kbrsphere.user_management.controller;

import com.kbrsphere.user_management.dto.ApiResponse;
import com.kbrsphere.user_management.dto.ApiStatus;
import com.kbrsphere.user_management.dto.LoginRequestDTO;
import com.kbrsphere.user_management.dto.LoginResponseDTO;
import com.kbrsphere.user_management.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserManagementService service;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS, "Login successful", service.login(request)));
    }
}