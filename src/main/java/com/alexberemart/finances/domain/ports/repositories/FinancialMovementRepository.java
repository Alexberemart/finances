package com.alexberemart.finances.domain.ports.repositories;

import com.alexberemart.finances.domain.models.FinancialMovement;
import java.util.List;

public interface FinancialMovementRepository {
    void saveAll(List<FinancialMovement> movements);
    List<FinancialMovement> findByBankAccountId(String bankAccountId);
    // Add other methods as needed
}