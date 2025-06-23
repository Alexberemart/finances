package com.alexberemart.finances.domain.ports.repositories;

import com.alexberemart.finances.domain.models.FinancialMovement;
import java.util.List;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;

public interface FinancialMovementRepository {
    void saveAll(List<FinancialMovement> movements);
}