package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.repositories.DraftFinancialMovementRepository;

public class DeleteAllDraftFinancialMovements {

    private final DraftFinancialMovementRepository repository;

    public DeleteAllDraftFinancialMovements(DraftFinancialMovementRepository repository) {
        this.repository = repository;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}