package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.DraftFinancialMovementRepository;

import java.util.List;

public class GetAllDraftFinancialMovements {

    private final DraftFinancialMovementRepository repository;

    public GetAllDraftFinancialMovements(DraftFinancialMovementRepository repository) {
        this.repository = repository;
    }

    public List<DraftFinancialMovementDto> execute() {
        return repository.findAll();
    }
}