-- ==============================
--        TABLA USUARIOS
-- ==============================
CREATE TABLE IF NOT EXISTS usuarios (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ==============================
--        TABLA TÓPICOS
-- ==============================
CREATE TABLE IF NOT EXISTS topicos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(50) NOT NULL DEFAULT 'NO_RESPONDIDO',
  autor VARCHAR(120) NOT NULL,
  curso VARCHAR(120) NOT NULL,
  UNIQUE (titulo, mensaje)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ==============================
--     INSERT ADMIN (EJEMPLO)
-- ==============================
-- Reemplaza el hash por un BCrypt REAL (12 rounds recomendado)
-- Ejemplo de comando en Spring para generar uno:
-- new BCryptPasswordEncoder().encode("admin123")
INSERT INTO usuarios (username, password, role) VALUES
('root', '$2y$10$20C.sKoKuvPQLBxlyiXije06p8M/beG4w1zWQQG7FV6OGMPitVypS', 'ROLE_ADMIN');
