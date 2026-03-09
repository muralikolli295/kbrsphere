package com.kbrsphere.user_management.controller;

import com.kbrsphere.user_management.model.User;
import com.kbrsphere.user_management.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/registration")
    public User saveUser(@RequestBody User user) { return userManagementService.saveUser(user); }

    @GetMapping("/getUser")
    public Optional<User> getUserById(@RequestParam Long id){
        return userManagementService.getUserById(id);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userManagementService.getUsers();
    }

    @PostMapping("/login")
    public Map<String,Object> userLogin(@RequestParam String userEmail, @RequestParam String password){
        return userManagementService.userLogin(userEmail,password);
    }

}
