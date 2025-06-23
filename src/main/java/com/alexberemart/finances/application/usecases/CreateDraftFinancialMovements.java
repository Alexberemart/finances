package com.alexberemart.finances.application.usecases;

import java.util.ArrayList;
import java.util.List;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;

public class CreateDraftFinancialMovements {

    public List<ImportMovementDto> create(List<FinancialMovementDto> financialMovements) throws Exception {
        List<ImportMovementDto> result = new ArrayList<>();
        for (FinancialMovementDto dto : financialMovements) {
            ImportMovementDto importDto = new ImportMovementDto();
            importDto.setDate(dto.getDate());
            importDto.setDescription(dto.getDescription());
            importDto.setAmount(dto.getAmount());
            // Map more fields if needed
            result.add(importDto);
        }
        return result;
    }
}