package com.kbrsphere.user_management.controller;

import com.kbrsphere.shared.enums.ApiStatus;
import com.kbrsphere.shared.response.ApiResponse;
import com.kbrsphere.user_management.dto.*;
import com.kbrsphere.user_management.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserManagementController {

    private final UserManagementService service;

    public UserManagementController(UserManagementService service) {
        this.service = service;
    }

    // REGISTER
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.status(201)
                .body(new ApiResponse<>(ApiStatus.SUCCESS, "User registered successfully", service.register(request)));
    }

    // GET ALL USERS (ADMIN)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers() {
        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS, "Users fetched successfully", service.getUsers()));
    }

    // GET USER BY ID (ADMIN OR SELF)
    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable String id) {
        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS, "User fetched successfully", service.getUserById(id)));
    }

    // UPDATE USER DETAILS BY ID (ADMIN OR SELF)
    @PatchMapping("/{id}")
    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateDetails(@PathVariable String id, @RequestBody UpdateUserDTO request) {
        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS,"Details updated successfully",service.updateUserDetails(id, request)));
    }

    // UPDATE USER PASSWORD BY ID
    @PatchMapping("/updatePassword/{id}")
    @PreAuthorize("#id == authentication.name")
    public ResponseEntity<ApiResponse<String>> updatePassword(@Valid @PathVariable String id, @Valid @RequestBody UpdatePasswordDTO request) {
        service.updatePassword(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS,"Password updated successfully",null));
    }

    // DELETE USER ACCOUNT BY ID
    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteUserAccount(@PathVariable String id){
        service.deleteAccount(id);

        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS,"Account deleted successfully",null));
    }

    // GET ALL OWNERS/CUSTOMERS BY ROLE
    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsersByRole(@PathVariable String role){
        return ResponseEntity.ok(
                new ApiResponse<>(ApiStatus.SUCCESS,"Users fetched successfully", service.getUsersByRole(role)));
    }
}