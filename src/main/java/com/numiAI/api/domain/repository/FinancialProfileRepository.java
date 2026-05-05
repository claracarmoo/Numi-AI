package com.numiAI.api.domain.repository;

import com.numiAI.api.domain.model.FinancialProfile;

import java.util.Optional;
import java.util.UUID;

public interface FinancialProfileRepository {
    FinancialProfile save(FinancialProfile profile);
    Optional<FinancialProfile> findByUserId(UUID userId);
}
