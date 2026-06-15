-- data.sql - Simple working version for H2
INSERT INTO users (username, email, password, role, full_name, is_active, created_at) 
VALUES (
    'admin',
    'admin@school.com',
    '$2a$10$N.ZuPqC5LQwJjK5L5qjK5eL5qjK5eL5qjK5eL5qjK5eL5qjK5eL5qjK5e',
    'ADMIN',
    'System Administrator',
    true,
    NOW()
);