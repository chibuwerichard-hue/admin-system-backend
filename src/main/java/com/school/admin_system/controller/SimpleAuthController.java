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
@RequestMapping("/api/simple-auth")
public class SimpleAuthController {

    @Autowired
    private UserRepository userRepository;
    
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
            response.put("success", true);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("message", "Login successful! Password matches.");
            response.put("userId", user.getId());
        } else {
            response.put("error", "Invalid password");
            response.put("stored_hash_prefix", user.getPassword().substring(0, 20));
            response.put("message", "Password does not match. Use admin123 as password");
        }
        
        return response;
    }
}
