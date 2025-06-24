package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ReplaceAllDraftFinancialMovements {

    private final DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements;
    private final SaveDraftFinancialMovements saveDraftFinancialMovements;

    public ReplaceAllDraftFinancialMovements(
            DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements,
            SaveDraftFinancialMovements saveDraftFinancialMovements) {
        this.deleteAllDraftFinancialMovements = deleteAllDraftFinancialMovements;
        this.saveDraftFinancialMovements = saveDraftFinancialMovements;
    }

    public void replaceAll(List<DraftFinancialMovementDto> newMovements) {
        deleteAllDraftFinancialMovements.deleteAll();
        saveDraftFinancialMovements.save(newMovements);
    }
}