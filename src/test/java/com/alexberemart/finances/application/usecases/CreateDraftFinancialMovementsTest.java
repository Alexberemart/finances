package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateDraftFinancialMovementsTest {

    @Test
    void create_shouldMapFinancialMovementsToImportMovements() throws Exception {
        // Arrange
        FinancialMovementDto dto1 = new FinancialMovementDto();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2024, Calendar.JUNE, 15, 0, 0, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        dto1.setDate(cal1.getTime());
        dto1.setDescription("Test movement 1");
        dto1.setAmount(new BigDecimal("100.00").doubleValue());

        FinancialMovementDto dto2 = new FinancialMovementDto();
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2024, Calendar.JUNE, 16, 0, 0, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        dto2.setDate(cal2.getTime());
        dto2.setDescription("Test movement 2");
        dto2.setAmount(new BigDecimal("200.00").doubleValue());

        List<FinancialMovementDto> inputList = Arrays.asList(dto1, dto2);

        CreateDraftFinancialMovements useCase = new CreateDraftFinancialMovements();

        // Act
        List<ImportMovementDto> result = useCase.create(inputList);

        // Assert
        assertEquals(2, result.size());

        ImportMovementDto import1 = result.get(0);
        assertEquals(dto1.getDate(), import1.getDate());
        assertEquals(dto1.getDescription(), import1.getDescription());
        assertEquals(dto1.getAmount(), import1.getAmount());

        ImportMovementDto import2 = result.get(1);
        assertEquals(dto2.getDate(), import2.getDate());
        assertEquals(dto2.getDescription(), import2.getDescription());
        assertEquals(dto2.getAmount(), import2.getAmount());
    }
}