package com.numiAI.api.domain.model;

import com.numiAI.api.domain.exception.DomainException;

import java.util.UUID;

public class User {

    private final UUID id;
    private String name;
    private String email;
    private String passwordHash;

    public User(UUID id, String name, String email, String passwordHash) {
        validate(name, email);
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    private void validate(String name, String email) {
        if (name == null || name.isBlank()) throw new DomainException("Nome do usuário é obrigatório");
        if (email == null || !email.contains("@")) throw new DomainException("E-mail inválido");
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
}
