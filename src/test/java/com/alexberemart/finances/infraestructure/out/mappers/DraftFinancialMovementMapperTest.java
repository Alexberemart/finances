package com.alexberemart.finances.infraestructure.out.mappers;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
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
        dto.setLabel("label");
        dto.setSkip(true);

        // Act
        ImportFinancialMovementEntity entity = DraftFinancialMovementMapper.toEntity(dto);

        // Assert
        assertEquals(dto.getDate(), entity.getDate());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getAmount(), entity.getAmount());
        assertEquals(dto.getLabel(), entity.getLabel());
        assertEquals(dto.getSkip(), entity.isSkip());
    }

    @Test
    void toDto_shouldMapEntityToDtoCorrectly() {
        // Arrange
        ImportFinancialMovementEntity entity = new ImportFinancialMovementEntity();
        Date now = new Date();
        entity.setDate(now);
        entity.setDescription("desc");
        entity.setAmount(BigDecimal.TEN);
        entity.setLabel("label");
        entity.setSkip(true);

        // Act
        DraftFinancialMovementDto dto = DraftFinancialMovementMapper.toDto(entity);

        // Assert
        assertEquals(entity.getDate(), dto.getDate());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getAmount(), dto.getAmount());
        assertEquals(entity.getLabel(), dto.getLabel());
        assertEquals(entity.isSkip(), dto.getSkip());
    }
}