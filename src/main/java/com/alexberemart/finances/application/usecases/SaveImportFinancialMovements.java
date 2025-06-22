package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;
import java.util.List;

public class SaveImportFinancialMovements {

    private final ImportFinancialMovementRepository repository;

    public SaveImportFinancialMovements(ImportFinancialMovementRepository repository) {
        this.repository = repository;
    }

    public void save(List<ImportFinancialMovementDto> importFinancialMovements) {
        repository.saveAll(importFinancialMovements);
    }
}
