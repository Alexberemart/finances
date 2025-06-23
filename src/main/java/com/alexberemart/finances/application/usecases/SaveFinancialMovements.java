package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SaveFinancialMovements {

    private final FinancialMovementRepository repository;

    public SaveFinancialMovements(FinancialMovementRepository repository) {
        this.repository = repository;
    }

    public void save(List<ImportFinancialMovementDto> importDtos) {
        List<FinancialMovement> movements = importDtos.stream()
            .map(dto -> {
                FinancialMovement fm = new FinancialMovement();
                fm.setDate(dto.getDate());
                fm.setDescription(dto.getDescription());
                fm.setAmount(dto.getAmount());
                fm.setLabel(dto.getLabel());
                return fm;
            })
            .collect(Collectors.toList());
        repository.saveAll(movements);
    }
}