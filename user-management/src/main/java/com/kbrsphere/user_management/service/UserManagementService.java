package com.kbrsphere.user_management.service;

import com.kbrsphere.user_management.config.JwtUtil;
import com.kbrsphere.user_management.dto.*;
import com.kbrsphere.user_management.dto.Role;
import com.kbrsphere.user_management.exception.UserException;
import com.kbrsphere.user_management.model.User;
import com.kbrsphere.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    public UserResponseDTO register(UserRequestDTO request) {
        // Email already exists
        if (userRepository.existsByUserEmail(request.getUserEmail())) {
            throw new UserException("User already exists with this email, try with another");
        }

        // Admin already exists
        if (request.getRole() == Role.ADMIN && userRepository.existsByRole(Role.ADMIN)) {
            throw new UserException("Admin already exists");
        }

        // Create & Save User
        User registeredUser = userRepository.save(new User(null,request.getUserName(),request.getUserEmail(),passwordEncoder.encode(request.getUserPassword()),request.getPhoneNumber(),request.getRole()));
        return mapToResponseDTO(registeredUser);
    }

    // GET ALL USERS
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // GET USER BY ID
    public UserResponseDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));

        return mapToResponseDTO(user);
    }

    // LOGIN
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUserEmail(request.getUserEmail());
        if (user == null) {
            throw new UserException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
            throw new UserException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUserId(),user.getRole().name());

        return new LoginResponseDTO(token);
    }

    // MAPPER
    private UserResponseDTO mapToResponseDTO(User user) {
        return new UserResponseDTO(user.getUserId(),user.getUserName(),user.getUserEmail(),user.getPhoneNumber(),user.getRole());
    }

    public UserResponseDTO updateUserDetails(String id, UpdateUserDTO request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));

        // Update only allowed fields
        if (request.getUserName() != null) {
            user.setUserName(request.getUserName());
        }

        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        User updatedUser = userRepository.save(user);

        return mapToResponseDTO(updatedUser);
    }

    public void updatePassword(String id, UpdatePasswordDTO request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));

        // Validate old password
        if (!passwordEncoder.matches(request.getOldPassword(), user.getUserPassword())) {
            throw new UserException("Old password is incorrect");
        }

        // Prevent same password reuse
        if (passwordEncoder.matches(request.getNewPassword(), user.getUserPassword())) {
            throw new UserException("New password cannot be same as old password");
        }

        // Update password
        user.setUserPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}