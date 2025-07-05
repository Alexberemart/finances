package com.alexberemart.finances.infraestructure.out.mappers;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementCategoryEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DraftFinancialMovementMapperTest {

    @Test
    void toEntity_shouldMapDtoToEntityCorrectly() {
        // Arrange
        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        Date now = new Date();
        dto.setDate(now);
        dto.setDescription("desc");
        dto.setAmount(BigDecimal.TEN);
        dto.setCategoryId("cat-1");
        dto.setSkip(true);
        dto.setBankAccountId("acc-1");

        // Act
        ImportFinancialMovementEntity entity = DraftFinancialMovementMapper.toEntity(dto);

        // Assert
        assertEquals(dto.getDate(), entity.getDate());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getAmount(), entity.getAmount());
        assertEquals(dto.getSkip(), entity.isSkip());
        assertEquals(dto.getBankAccountId(), entity.getBankAccountId());
        assertNotNull(entity.getCategory());
        assertEquals(dto.getCategoryId(), entity.getCategory().getId());
    }

    @Test
    void toDto_shouldMapEntityToDtoCorrectly() {
        // Arrange
        ImportFinancialMovementEntity entity = new ImportFinancialMovementEntity();
        Date now = new Date();
        entity.setDate(now);
        entity.setDescription("desc");
        entity.setAmount(BigDecimal.TEN);
        entity.setSkip(true);
        entity.setBankAccountId("acc-1");
        FinancialMovementCategoryEntity category = new FinancialMovementCategoryEntity();
        category.setId("cat-1");
        entity.setCategory(category);

        // Act
        DraftFinancialMovementDto dto = DraftFinancialMovementMapper.toDto(entity);

        // Assert
        assertEquals(entity.getDate(), dto.getDate());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getAmount(), dto.getAmount());
        assertEquals(entity.isSkip(), dto.getSkip());
        assertEquals(entity.getBankAccountId(), dto.getBankAccountId());
        assertEquals(entity.getCategory().getId(), dto.getCategoryId());
    }
}