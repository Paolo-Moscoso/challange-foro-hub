-- ========================
-- Roles
-- ========================
INSERT INTO roles (id, name) VALUES (1, 'USER');
INSERT INTO roles (id, name) VALUES (2, 'ADMIN');

-- ========================
-- Usuarios
-- ========================
-- Nota: password encriptadas con BCrypt
-- admin123 => $2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36tH2K1E2/nzp2ZQ09ZcZFe
-- user123  => $2a$10$7Qj6HX4/YVbk5LgO.qN6UuJueWONJ3B6uQiG9rtgn0Z5pKkt4rOeW

INSERT INTO users (id, username, email, password) 
VALUES (1, 'admin', 'admin@foro.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36tH2K1E2/nzp2ZQ09ZcZFe');

INSERT INTO users (id, username, email, password) 
VALUES (2, 'usuario', 'user@foro.com', '$2a$10$7Qj6HX4/YVbk5LgO.qN6UuJueWONJ3B6uQiG9rtgn0Z5pKkt4rOeW');

-- ========================
-- Relación usuarios-roles
-- ========================
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2); -- Admin
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1); -- Usuario normal