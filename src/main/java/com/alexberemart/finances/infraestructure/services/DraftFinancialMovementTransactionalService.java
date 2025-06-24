package com.alexberemart.finances.infraestructure.services;

import com.alexberemart.finances.application.usecases.ReplaceAllDraftFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DraftFinancialMovementTransactionalService {

    private final ReplaceAllDraftFinancialMovements replaceAllDraftFinancialMovements;

    public DraftFinancialMovementTransactionalService(ReplaceAllDraftFinancialMovements replaceAllDraftFinancialMovements) {
        this.replaceAllDraftFinancialMovements = replaceAllDraftFinancialMovements;
    }

    @Transactional
    public void replaceAllDraftFinancialMovements(List<DraftFinancialMovementDto> newMovements) {
        replaceAllDraftFinancialMovements.replaceAll(newMovements);
    }
}