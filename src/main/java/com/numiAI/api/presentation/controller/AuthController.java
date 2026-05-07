package com.numiAI.api.presentation.controller;

import com.numiAI.api.domain.exception.DomainException;
import com.numiAI.api.domain.model.User;
import com.numiAI.api.domain.repository.UserRepository;
import com.numiAI.api.infrastructure.security.JwtService;
import com.numiAI.api.presentation.dto.AuthResponse;
import com.numiAI.api.presentation.dto.LoginRequest;
import com.numiAI.api.presentation.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DomainException("E-mail já cadastrado");
        }

        User user = new User(
                UUID.randomUUID(),
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password())
        );

        userRepository.save(user);
        String token = jwtService.generateToken(user.getId(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(token, user.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new DomainException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new DomainException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getId()));
    }
}
