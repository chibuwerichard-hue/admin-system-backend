package com.school.admin_system.config;

import com.school.admin_system.entity.User;
import com.school.admin_system.entity.UserRole;
import com.school.admin_system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if users already exist
            if (userRepository.count() == 0) {
                // Create Admin user
                User admin = new User();
                admin.setEmail("admin@school.com");
                admin.setPassword(passwordEncoder.encode("Admin@123"));
                admin.setRole(UserRole.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Created Admin user: admin@school.com");

                // Create Teacher user
                User teacher = new User();
                teacher.setEmail("teacher@school.com");
                teacher.setPassword(passwordEncoder.encode("Admin@123"));
                teacher.setRole(UserRole.TEACHER);
                userRepository.save(teacher);
                System.out.println("✅ Created Teacher user: teacher@school.com");

                // Create Finance user
                User finance = new User();
                finance.setEmail("finance@school.com");
                finance.setPassword(passwordEncoder.encode("Admin@123"));
                finance.setRole(UserRole.FINANCE_OFFICER);
                userRepository.save(finance);
                System.out.println("✅ Created Finance user: finance@school.com");

                // Create Sports user
                User sports = new User();
                sports.setEmail("sports@school.com");
                sports.setPassword(passwordEncoder.encode("Admin@123"));
                sports.setRole(UserRole.SPORTS_COORDINATOR);
                userRepository.save(sports);
                System.out.println("✅ Created Sports user: sports@school.com");

                System.out.println("✅ All test users created successfully!");
            } else {
                System.out.println("ℹ Users already exist in database");
            }
        };
    }
}