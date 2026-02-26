package com.kbrsphere.user_management.service;

import com.kbrsphere.user_management.dto.User;
import com.kbrsphere.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encryptPassword(String password){
       return passwordEncoder.encode(password);
    }

    public User saveUser(@RequestBody User user) {
        String encryptedPassword = encryptPassword(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(@RequestParam Long id){ return userRepository.findById(id); }

    public List<User> getUsers(){ return userRepository.findAll(); }

    public boolean verifyPassword(String rawPassword, String encodedPassword) { return passwordEncoder.matches(rawPassword, encodedPassword); }

}
