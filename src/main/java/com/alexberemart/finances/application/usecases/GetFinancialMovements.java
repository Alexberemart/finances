package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import java.util.List;

public class GetFinancialMovements {

    private final FinancialMovementRepository repository;

    public GetFinancialMovements(FinancialMovementRepository repository) {
        this.repository = repository;
    }

    public List<FinancialMovement> findByBankAccountId(String bankAccountId) {
        return repository.findByBankAccountId(bankAccountId);
    }
}