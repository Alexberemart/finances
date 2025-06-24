package com.alexberemart.finances.application.usecases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.DraftFinancialMovementRepository;

class SaveDraftFinancialMovementsTest {

    @Test
    void save_shouldDelegateToRepository() {
        // Arrange
        DraftFinancialMovementRepository mockRepo = mock(DraftFinancialMovementRepository.class);
        SaveDraftFinancialMovements useCase = new SaveDraftFinancialMovements(mockRepo);

        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        List<DraftFinancialMovementDto> dtos = List.of(dto);

        // Act
        useCase.save(dtos);

        // Assert
        verify(mockRepo, times(1)).saveAll(dtos);
    }
}