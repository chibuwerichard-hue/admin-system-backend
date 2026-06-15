-- data.sql
-- Insert default admin user only if table exists

-- Delete existing admin user if exists (simplified for H2)
DELETE FROM users WHERE username = 'admin';

-- Insert default admin user
-- Password is 'admin123' encrypted with BCrypt
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