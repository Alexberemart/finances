package com.alexberemart.finances.infraestructure.out.mappers;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementCategoryEntity;

public class DraftFinancialMovementMapper {

    public static ImportFinancialMovementEntity toEntity(DraftFinancialMovementDto dto) {
        ImportFinancialMovementEntity entity = new ImportFinancialMovementEntity();
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        // Map categoryId to FinancialMovementCategoryEntity
        if (dto.getCategoryId() != null) {
            FinancialMovementCategoryEntity category = new FinancialMovementCategoryEntity();
            category.setId(dto.getCategoryId());
            entity.setCategory(category);
        } else {
            entity.setCategory(null);
        }
        entity.setSkip(dto.getSkip() != null && dto.getSkip());
        entity.setBankAccountId(dto.getBankAccountId());
        return entity;
    }

    public static DraftFinancialMovementDto toDto(ImportFinancialMovementEntity entity) {
        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        dto.setDate(entity.getDate());
        dto.setDescription(entity.getDescription());
        dto.setAmount(entity.getAmount());
        // Map FinancialMovementCategoryEntity to categoryId
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setSkip(entity.isSkip());
        dto.setBankAccountId(entity.getBankAccountId());
        return dto;
    }
}