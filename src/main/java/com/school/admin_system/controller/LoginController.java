package com.school.admin_system.controller;

import com.school.admin_system.entity.User;
import com.school.admin_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        Map<String, Object> response = new HashMap<>();
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        
        User user = userRepository.findByEmail(email).orElse(null);
        
        if (user == null) {
            response.put("error", "User not found with email: " + email);
            return response;
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, user.getPassword())) {
            response.put("success", true);
            response.put("message", "Login successful!");
            response.put("email", user.getEmail());
            response.put("role", user.getRole().toString());
            response.put("userId", user.getId());
            response.put("isActive", user.getIsActive());
        } else {
            response.put("error", "Invalid credentials");
        }
        
        return response;
    }
}
