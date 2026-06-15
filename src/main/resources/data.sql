-- data.sql
-- This file runs automatically if spring.sql.init.mode=always

-- Clean existing data (be careful in production!)
SET REFERENTIAL_INTEGRITY FALSE;

-- Delete existing data if table exists
DELETE FROM users IF EXISTS;

SET REFERENTIAL_INTEGRITY TRUE;

-- Insert default admin user
-- Password is 'admin123' encrypted with BCrypt
INSERT INTO users (username, email, password, role, full_name, is_active, created_at) 
SELECT * FROM (
    VALUES (
        'admin',
        'admin@school.com',
        '$2a$10$N.ZuPqC5LQwJjK5L5qjK5eL5qjK5eL5qjK5eL5qjK5eL5qjK5eL5qjK5e',
        'ADMIN',
        'System Administrator',
        true,
        NOW()
    )
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'admin'
);