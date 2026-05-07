package com.numiAI.api.domain.exception;

public class ResourceNotFoundException extends DomainException {
    public ResourceNotFoundException(String resource, Object id) {
        super(resource + " não encontrado(a) com id: " + id);
    }
}
