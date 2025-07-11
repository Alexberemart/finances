package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.DraftFinancialMovementRepository;
import java.util.List;

public class SaveDraftFinancialMovements {

    private final DraftFinancialMovementRepository repository;

    public SaveDraftFinancialMovements(DraftFinancialMovementRepository repository) {
        this.repository = repository;
    }

    public void save(List<DraftFinancialMovementDto> draftFinancialMovements) {
        repository.saveAll(draftFinancialMovements);
    }
}