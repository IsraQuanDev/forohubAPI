

Use forohub;
SELECT * FROM usuarios;

UPDATE usuarios
SET password = '$2a$10$uYzlOgokcq6afujqgFtVCORA8lyd/iR5CwAqfsSiK8x1BOBZe.9KS'
WHERE username = 'admin';


SELECT * FROM usuarios;