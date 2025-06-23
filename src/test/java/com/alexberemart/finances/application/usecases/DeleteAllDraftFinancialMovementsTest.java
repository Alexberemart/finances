package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteAllDraftFinancialMovementsTest {

    @Test
    void deleteAll_shouldDelegateToRepository() {
        // Arrange
        ImportFinancialMovementRepository mockRepo = mock(ImportFinancialMovementRepository.class);
        DeleteAllDraftFinancialMovements useCase = new DeleteAllDraftFinancialMovements(mockRepo);

        // Act
        useCase.deleteAll();

        // Assert
        verify(mockRepo, times(1)).deleteAll();
    }
}