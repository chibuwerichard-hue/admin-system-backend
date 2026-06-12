package com.school.admin_system.controller;

import com.school.admin_system.entity.User;
import com.school.admin_system.entity.UserRole;
import com.school.admin_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, String> userData) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String email = userData.get("email");
            String password = userData.get("password");
            String roleStr = userData.get("role");
            String fullName = userData.get("fullName");
            
            // Check if user already exists
            if (userRepository.findByEmail(email).isPresent()) {
                response.put("error", "User with this email already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            
            // Convert role string to UserRole enum
            UserRole role;
            try {
                role = UserRole.valueOf(roleStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                response.put("error", "Invalid role. Valid roles: ADMIN, TEACHER, FINANCE_OFFICER, STUDENT");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            // Create new user
            User user = new User();
            user.setEmail(email);
            // In production, encode password properly
            user.setPassword(password); // For testing only
            user.setRole(role);
            user.setIsActive(true);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            
            User savedUser = userRepository.save(user);
            
            response.put("success", true);
            response.put("message", "User created successfully");
            response.put("user", Map.of(
                "id", savedUser.getId(),
                "email", savedUser.getEmail(),
                "role", savedUser.getRole(),
                "fullName", fullName != null ? fullName : ""
            ));
            
            System.out.println("✅ User created: " + email + " with role: " + role);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            System.out.println("❌ Error creating user: " + e.getMessage());
            response.put("error", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> userData) {
        Map<String, Object> response = new HashMap<>();
        
        return userRepository.findById(id)
                .map(user -> {
                    if (userData.containsKey("isActive")) {
                        user.setIsActive((Boolean) userData.get("isActive"));
                    }
                    if (userData.containsKey("role")) {
                        try {
                            user.setRole(UserRole.valueOf(((String) userData.get("role")).toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            response.put("error", "Invalid role");
                        }
                    }
                    user.setUpdatedAt(LocalDateTime.now());
                    userRepository.save(user);
                    
                    response.put("success", true);
                    response.put("message", "User updated successfully");
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            response.put("success", true);
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        }
        
        response.put("error", "User not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        long totalUsers = userRepository.count();
        analytics.put("totalUsers", totalUsers);
        analytics.put("systemStatus", "Running");
        analytics.put("message", "AI Analytics Dashboard - Ready");
        return ResponseEntity.ok(analytics);
    }
}
