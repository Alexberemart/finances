package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;
import java.util.List;

public class SaveDraftFinancialMovements {

    private final ImportFinancialMovementRepository repository;

    public SaveDraftFinancialMovements(ImportFinancialMovementRepository repository) {
        this.repository = repository;
    }

    public void save(List<DraftFinancialMovementDto> draftFinancialMovements) {
        repository.saveAll(draftFinancialMovements);
    }
}