package com.school.admin_system.controller;

import com.school.admin_system.entity.User;
import com.school.admin_system.entity.UserRole;
import com.school.admin_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, Object> userData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = (String) userData.get("email");
            String password = (String) userData.get("password");
            String role = (String) userData.get("role");
            Boolean isActive = (Boolean) userData.getOrDefault("isActive", true);

            if (userRepository.existsByEmail(email)) {
                response.put("success", false);
                response.put("message", "User with this email already exists");
                return ResponseEntity.badRequest().body(response);
            }

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(UserRole.valueOf(role));
            user.setIsActive(isActive);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            User savedUser = userRepository.save(user);
            
            response.put("success", true);
            response.put("message", "User created successfully");
            response.put("user", savedUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            response.put("success", true);
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "User not found");
            return ResponseEntity.notFound().build();
        }
    }
}
