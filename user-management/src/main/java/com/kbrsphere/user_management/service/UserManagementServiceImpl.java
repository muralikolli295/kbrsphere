package com.kbrsphere.user_management.service;

import com.kbrsphere.user_management.config.JwtUtil;
import com.kbrsphere.user_management.dto.*;
import com.kbrsphere.user_management.dto.Role;
import com.kbrsphere.shared.exception.UserException;
import com.kbrsphere.user_management.model.User;
import com.kbrsphere.user_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserManagementServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

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
        User registeredUser = userRepository.save(new User(null, request.getUserName(), request.getUserEmail(), passwordEncoder.encode(request.getUserPassword()), request.getPhoneNumber(), request.getRole()));
        return mapToResponseDTO(registeredUser);
    }

    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    public UserResponseDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));

        return mapToResponseDTO(user);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUserEmail(request.getUserEmail());
        if (user == null) {
            throw new UserException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
            throw new UserException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUserId(), user.getRole().name());
        return new LoginResponseDTO(token);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        return new UserResponseDTO(user.getUserId(), user.getUserName(),user.getUserEmail(), user.getPhoneNumber(),user.getRole());
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

    public void deleteAccount(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserResponseDTO> getUsersByRole(String role) {
        List<User> usersByRole = userRepository.findAllByRole(Role.valueOf(role));
        return usersByRole.stream().map(this::mapToResponseDTO).toList();
    }
}