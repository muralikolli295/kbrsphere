package com.kbrsphere.user_management.service;

import com.kbrsphere.user_management.config.JwtUtil;
import com.kbrsphere.user_management.model.User;
import com.kbrsphere.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

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

    public Map<String, Object> userLogin(String userEmail, String password) {
        Map<String, Object> loginResponse = new HashMap<>();

        User user = userRepository.findByUserEmail(userEmail);
        if(user == null){
            loginResponse.put("status","failed");
            loginResponse.put("message","User doesn't exist, please register");
            loginResponse.put("token","");
            return loginResponse;
        }

        if (verifyPassword(password, user.getUserPassword())) {
            String token = jwtUtil.generateToken(userEmail);
            loginResponse.put("status", "success");
            loginResponse.put("message","login successful");
            loginResponse.put("token", token);

            return loginResponse;
        }

        loginResponse.put("status", "failed");
        loginResponse.put("message","login failed, please enter valid password");
        loginResponse.put("token", "");

        return loginResponse;
    }
}
