package com.school.admin_system.controller;

import com.school.admin_system.entity.User;
import com.school.admin_system.repository.UserRepository;
import com.school.admin_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/simple-auth")
public class SimpleAuthController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        Map<String, Object> response = new HashMap<>();
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        
        User user = userRepository.findByUsername(username).orElse(null);
        
        if (user == null) {
            response.put("error", "User not found");
            return response;
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            response.put("success", true);
            response.put("token", token);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("message", "Login successful!");
        } else {
            response.put("error", "Invalid password");
            // Debug info
            response.put("stored_hash", user.getPassword());
            response.put("provided_password", password);
        }
        
        return response;
    }
}
