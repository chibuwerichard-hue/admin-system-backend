-- data.sql
-- This file runs automatically if spring.sql.init.mode=always

-- Clean existing data (be careful in production!)
SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE users;
SET REFERENTIAL_INTEGRITY TRUE;

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

-- Insert sample student data (optional)
INSERT INTO students (name, class_name, teacher_id) VALUES
('John Doe', 'Class 10A', NULL),
('Jane Smith', 'Class 10A', NULL),
('Mike Johnson', 'Class 10B', NULL);

-- Insert sample transactions (optional)
INSERT INTO transactions (type, amount, category, description, date) VALUES
('PAYMENT', 50000.00, 'Fees', 'Tuition fees collection', NOW()),
('EXPENSE', 15000.00, 'Salary', 'Teacher salaries', NOW());