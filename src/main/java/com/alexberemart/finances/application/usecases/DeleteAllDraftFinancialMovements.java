package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;

public class DeleteAllDraftFinancialMovements {

    private final ImportFinancialMovementRepository repository;

    public DeleteAllDraftFinancialMovements(ImportFinancialMovementRepository repository) {
        this.repository = repository;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}