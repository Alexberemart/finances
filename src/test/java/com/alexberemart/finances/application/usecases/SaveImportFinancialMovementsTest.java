package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class SaveImportFinancialMovementsTest {

    @Test
    void save_shouldDelegateToRepository() {
        // Arrange
        ImportFinancialMovementRepository mockRepo = mock(ImportFinancialMovementRepository.class);
        SaveImportFinancialMovements useCase = new SaveImportFinancialMovements(mockRepo);

        ImportFinancialMovementDto dto = new ImportFinancialMovementDto();
        List<ImportFinancialMovementDto> dtos = List.of(dto);

        // Act
        useCase.save(dtos);

        // Assert
        verify(mockRepo, times(1)).saveAll(dtos);
    }
}
