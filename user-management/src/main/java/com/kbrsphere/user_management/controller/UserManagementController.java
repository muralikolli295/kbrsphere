package com.kbrsphere.user_management.controller;

import com.kbrsphere.shared.enums.ApiStatus;
import com.kbrsphere.shared.response.ApiResponse;
import com.kbrsphere.user_management.dto.UpdatePasswordDTO;
import com.kbrsphere.user_management.dto.UpdateUserDTO;
import com.kbrsphere.user_management.dto.UserRequestDTO;
import com.kbrsphere.user_management.dto.UserResponseDTO;
import com.kbrsphere.user_management.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ApiStatus.SUCCESS, "User registered successfully", service.register(request)));
    }

    // GET ALL USERS - ADMIN ONLY
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ApiStatus.SUCCESS, "Users fetched successfully", service.getUsers()));
    }

    // GET USER BY ID - ADMIN OR SELF
    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ApiStatus.SUCCESS, "User fetched successfully", service.getUserById(id)));
    }

    // UPDATE USER DETAILS - ADMIN OR SELF
    @PatchMapping("/{id}")
    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateDetails(@PathVariable String id, @Valid @RequestBody UpdateUserDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ApiStatus.SUCCESS, "Details updated successfully", service.updateUserDetails(id, request)));
    }

    // UPDATE USER PASSWORD - SELF ONLY
    @PatchMapping("/{id}/password")
    @PreAuthorize("#id == authentication.name")
    public ResponseEntity<ApiResponse<String>> updatePassword(@PathVariable String id, @Valid @RequestBody UpdatePasswordDTO request) {
        service.updatePassword(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ApiStatus.SUCCESS, "Password updated successfully", null));
    }

    // DELETE USER ACCOUNT - ADMIN OR SELF
    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteUserAccount(@PathVariable String id) {
        service.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ApiStatus.SUCCESS, "Account deleted successfully", null));
    }

    // GET USERS BY ROLE - ADMIN ONLY
    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsersByRole(@PathVariable String role) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ApiStatus.SUCCESS, "Users fetched successfully", service.getUsersByRole(role)));
    }
}