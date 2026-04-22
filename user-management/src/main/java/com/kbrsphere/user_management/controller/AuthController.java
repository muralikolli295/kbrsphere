package com.kbrsphere.user_management.controller;

import com.kbrsphere.shared.enums.ApiStatus;
import com.kbrsphere.shared.response.ApiResponse;
import com.kbrsphere.user_management.dto.LoginRequestDTO;
import com.kbrsphere.user_management.dto.LoginResponseDTO;
import com.kbrsphere.user_management.service.UserManagementServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserManagementServiceImpl service;

    public AuthController(UserManagementServiceImpl service) {
        this.service = service;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS, "Login successful", service.login(request)));
    }
}