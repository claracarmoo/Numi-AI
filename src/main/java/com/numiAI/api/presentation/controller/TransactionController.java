package com.numiAI.api.presentation.controller;

import com.numiAI.api.application.dto.CreateTransactionCommand;
import com.numiAI.api.application.port.in.ManageTransactionUseCase;
import com.numiAI.api.presentation.dto.TransactionRequest;
import com.numiAI.api.presentation.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ManageTransactionUseCase useCase;

    private UUID getAuthenticatedUserId() {
        return (UUID) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(
            @Valid @RequestBody TransactionRequest request) {
        var command = new CreateTransactionCommand(
                getAuthenticatedUserId(),
                request.description(),
                request.amount(),
                request.type(),
                request.category(),
                request.date());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.from(useCase.create(command)));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> list() {
        return ResponseEntity.ok(
                useCase.listByUser(getAuthenticatedUserId())
                        .stream().map(TransactionResponse::from).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id, getAuthenticatedUserId());
        return ResponseEntity.noContent().build();
    }
}
