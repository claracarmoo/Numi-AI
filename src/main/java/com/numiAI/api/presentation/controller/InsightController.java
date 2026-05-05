package com.numiAI.api.presentation.controller;

import com.numiAI.api.application.port.in.GenerateInsightUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/insights")
public class InsightController {

    private final GenerateInsightUseCase useCase;

    public InsightController(GenerateInsightUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/report/{year}/{month}")
    public ResponseEntity<String> monthlyReport(@PathVariable int year,
                                                @PathVariable int month,
                                                @RequestHeader("X-User-Id") UUID userId) {
        return ResponseEntity.ok(useCase.generateMonthlyReport(userId, year, month));
    }

    @PostMapping("/ask")
    public ResponseEntity<String> ask(@RequestBody String question,
                                      @RequestHeader("X-User-Id") UUID userId) {
        return ResponseEntity.ok(useCase.answerQuestion(userId, question));
    }
}
