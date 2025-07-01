package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ReplaceAllDraftFinancialMovementsTest {

    @Test
    void replaceAll_shouldDeleteAllAndSaveNewMovements() {
        // Arrange
        DeleteAllDraftFinancialMovements mockDelete = mock(DeleteAllDraftFinancialMovements.class);
        SaveDraftFinancialMovements mockSave = mock(SaveDraftFinancialMovements.class);

        ReplaceAllDraftFinancialMovements useCase = new ReplaceAllDraftFinancialMovements(mockDelete, mockSave);

        List<DraftFinancialMovementDto> newMovements = List.of(new DraftFinancialMovementDto(), new DraftFinancialMovementDto());

        // Act
        useCase.replaceAll(newMovements);

        // Assert
        verify(mockDelete, times(1)).deleteAll();
        verify(mockSave, times(1)).save(newMovements);
        verifyNoMoreInteractions(mockDelete, mockSave);
    }
}