package com.project.forohub.service;

import com.project.forohub.entity.Usuario;
import com.project.forohub.repository.UsuarioRepository;
import com.project.forohub.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(String username, String password) {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña inválidos"));
        if (!passwordEncoder.matches(password, u.getPassword())) {
            throw new RuntimeException("Usuario o contraseña inválidos");
        }
        return jwtUtil.generateToken(username);
    }
}
