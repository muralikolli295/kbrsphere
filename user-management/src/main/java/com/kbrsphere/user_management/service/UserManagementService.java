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

        // Create user
        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());

        User saved = userRepository.save(user);

        return mapToResponseDTO(saved);
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
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

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
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getUserEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        return dto;
    }
}