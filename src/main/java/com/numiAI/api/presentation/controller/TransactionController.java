package com.numiAI.api.presentation.controller;

import com.numiAI.api.application.dto.CreateTransactionCommand;
import com.numiAI.api.application.port.in.ManageTransactionUseCase;
import com.numiAI.api.presentation.dto.TransactionRequest;
import com.numiAI.api.presentation.dto.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final ManageTransactionUseCase useCase;

    public TransactionController(ManageTransactionUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest request,
                                                      @RequestHeader("X-User-Id") UUID userId) {
        var command = new CreateTransactionCommand(
                userId, request.description(), request.amount(),
                request.type(), request.category(), request.date());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.from(useCase.create(command)));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> list(@RequestHeader("X-User-Id") UUID userId) {
        return ResponseEntity.ok(useCase.listByUser(userId).stream().map(TransactionResponse::from).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id,
                                       @RequestHeader("X-User-Id") UUID userId) {
        useCase.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}
