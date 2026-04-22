package com.kbrsphere.user_management.service;

import com.kbrsphere.user_management.dto.*;

import java.util.List;

public interface UserManagementService {

    UserResponseDTO register(UserRequestDTO request);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUserById(String id);

    LoginResponseDTO login(LoginRequestDTO request);

    UserResponseDTO updateUserDetails(String id, UpdateUserDTO request);

    void updatePassword(String id, UpdatePasswordDTO request);

    void deleteAccount(String id);

    List<UserResponseDTO> getUsersByRole(String role);
}
