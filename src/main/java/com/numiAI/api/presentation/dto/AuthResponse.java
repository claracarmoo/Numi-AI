package com.numiAI.api.presentation.dto;

import java.util.UUID;

public record AuthResponse(String token,
                           UUID userId) {
}
