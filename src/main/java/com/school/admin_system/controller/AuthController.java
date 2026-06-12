package com.school.admin_system.controller;

import com.school.admin_system.entity.User;
import com.school.admin_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            
            System.out.println("=== LOGIN ATTEMPT ===");
            System.out.println("Email: " + email);
            
            Optional<User> userOpt = userRepository.findByEmail(email);
            
            if (!userOpt.isPresent()) {
                System.out.println("User not found: " + email);
                response.put("error", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            User user = userOpt.get();
            System.out.println("User found: " + user.getEmail());
            System.out.println("User role: " + user.getRole());
            
            // Accept any password for testing
            String token = "temp_token_" + user.getId() + "_" + System.currentTimeMillis();
            
            response.put("success", true);
            response.put("token", token);
            response.put("email", user.getEmail());
            response.put("role", user.getRole().toString());
            response.put("id", user.getId());
            response.put("message", "Login successful");
            
            System.out.println("✅ Login successful for: " + email);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.out.println("❌ Error in login: " + e.getMessage());
            e.printStackTrace();
            response.put("error", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "School Management System");
        response.put("message", "Auth endpoints are working!");
        return ResponseEntity.ok(response);
    }
}
