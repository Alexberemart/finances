package com.alexberemart.finances.infraestructure.services;

import com.alexberemart.finances.application.usecases.ReplaceAllDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.GetAllDraftFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DraftFinancialMovementTransactionalService {

    private final ReplaceAllDraftFinancialMovements replaceAllDraftFinancialMovements;
    private final GetAllDraftFinancialMovements getAllDraftFinancialMovements;

    public DraftFinancialMovementTransactionalService(
        ReplaceAllDraftFinancialMovements replaceAllDraftFinancialMovements,
        GetAllDraftFinancialMovements getAllDraftFinancialMovements
    ) {
        this.replaceAllDraftFinancialMovements = replaceAllDraftFinancialMovements;
        this.getAllDraftFinancialMovements = getAllDraftFinancialMovements;
    }

    @Transactional
    public void replaceAllDraftFinancialMovements(List<DraftFinancialMovementDto> newMovements) {
        replaceAllDraftFinancialMovements.replaceAll(newMovements);
    }

    public List<DraftFinancialMovementDto> getAllDraftFinancialMovements() {
        return getAllDraftFinancialMovements.execute();
    }
}