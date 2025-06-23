package com.alexberemart.finances.infraestructure.out.mappers;

import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;

public class ImportFinancialMovementMapper {
    public static ImportFinancialMovementEntity toEntity(ImportFinancialMovementDto dto) {
        ImportFinancialMovementEntity entity = new ImportFinancialMovementEntity();
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setLabel(dto.getLabel());
        entity.setSkip(dto.getSkip() != null && dto.getSkip());
        return entity;
    }
}