package com.school.admin_system.controller;

import com.school.admin_system.entity.User;
import com.school.admin_system.entity.UserRole;
import com.school.admin_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminSetupController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/setup-admin")
    public String setupAdmin() {
        if (userRepository.findByEmail("admin@school.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@school.com");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(UserRole.ADMIN);
            admin.setIsActive(true);
            userRepository.save(admin);
            
            return "✅ Admin user created successfully!\nEmail: admin@school.com\nPassword: admin123";
        } else {
            return "ℹ️ Admin user already exists!\nEmail: admin@school.com";
        }
    }
}
