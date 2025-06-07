package com.alexberemart.finances.infraestructure.out;

import java.util.ArrayList;
import java.util.List;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;
import com.alexberemart.finances.domain.ports.in.iFinancialMovementBankPort;

public class FinancialMovementBankPort implements iFinancialMovementBankPort {

  @Override
  public List<ImportMovementDto> getFinancialMovementBankPort(List<FinancialMovementDto> financialMovements)
      throws Exception {
    List<ImportMovementDto> result = new ArrayList<>();
    for (FinancialMovementDto dto : financialMovements) {
        ImportMovementDto importDto = new ImportMovementDto();
        importDto.setDate(dto.getDate());
        importDto.setDescription(dto.getDescription());
        importDto.setAmount(dto.getAmount());
        // Si ImportMovementDto tiene más campos, mapéalos aquí
        result.add(importDto);
    }
    return result;
  }
}