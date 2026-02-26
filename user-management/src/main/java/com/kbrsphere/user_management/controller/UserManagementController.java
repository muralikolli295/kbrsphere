package com.kbrsphere.user_management.controller;

import com.kbrsphere.user_management.dto.User;
import com.kbrsphere.user_management.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private UserManagementService userServiceService;

    @PostMapping("/registration")
    public User saveUser(@RequestBody User user) { return userServiceService.saveUser(user); }

    @GetMapping("/getUser")
    public Optional<User> getUserById(@RequestParam Long id){
        return userServiceService.getUserById(id);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userServiceService.getUsers();
    }

}
