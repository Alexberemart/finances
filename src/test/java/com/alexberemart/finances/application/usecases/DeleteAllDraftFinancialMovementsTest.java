package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.repositories.DraftFinancialMovementRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteAllDraftFinancialMovementsTest {

    @Test
    void deleteAll_shouldDelegateToRepository() {
        // Arrange
        DraftFinancialMovementRepository mockRepo = mock(DraftFinancialMovementRepository.class);
        DeleteAllDraftFinancialMovements useCase = new DeleteAllDraftFinancialMovements(mockRepo);

        // Act
        useCase.deleteAll();

        // Assert
        verify(mockRepo, times(1)).deleteAll();
    }
}