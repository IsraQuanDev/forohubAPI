package com.project.forohub.controller;

import com.project.forohub.dto.LoginRequestDTO;
import com.project.forohub.dto.LoginResponseDTO;
import com.project.forohub.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {

        String token = tokenService.authenticate(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
