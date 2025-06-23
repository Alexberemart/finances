package com.alexberemart.finances.infraestructure.out.mappers;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ImportFinancialMovementMapperTest {

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
        ImportFinancialMovementEntity entity = ImportFinancialMovementMapper.toEntity(dto);

        // Assert
        assertEquals(dto.getDate(), entity.getDate());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getAmount(), entity.getAmount());
        assertEquals(dto.getLabel(), entity.getLabel());
        assertEquals(dto.getSkip(), entity.isSkip());
    }
}