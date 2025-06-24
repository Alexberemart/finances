package com.alexberemart.finances.infraestructure.out.mappers;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;

public class DraftFinancialMovementMapper {

    public static ImportFinancialMovementEntity toEntity(DraftFinancialMovementDto dto) {
        ImportFinancialMovementEntity entity = new ImportFinancialMovementEntity();
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setLabel(dto.getLabel());
        entity.setSkip(dto.getSkip() != null && dto.getSkip());
        return entity;
    }

    public static DraftFinancialMovementDto toDto(ImportFinancialMovementEntity entity) {
        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        dto.setDate(entity.getDate());
        dto.setDescription(entity.getDescription());
        dto.setAmount(entity.getAmount());
        dto.setLabel(entity.getLabel());
        dto.setSkip(entity.isSkip());
        return dto;
    }
}