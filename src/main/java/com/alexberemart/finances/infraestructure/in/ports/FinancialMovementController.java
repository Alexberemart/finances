package com.alexberemart.finances.infraestructure.in.ports;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;
import com.alexberemart.finances.domain.useCases.CreateFinancialMovements;
import com.alexberemart.finances.infraestructure.out.FinancialMovementBankPort;

@RestController
class FinancialMovementController {

  private final CreateFinancialMovements createFinancialMovements;
  private final FinancialMovementBankPort financialMovementBankPort;

  FinancialMovementController(CreateFinancialMovements createFinancialMovements,
      FinancialMovementBankPort financialMovementBankPort) {
    this.createFinancialMovements = createFinancialMovements;
    this.financialMovementBankPort = financialMovementBankPort;
  }

  @PostMapping("/financial-movements")
  List<ImportMovementDto> newEmployee(@RequestBody List<FinancialMovementDto> financialMovementDtos)
      throws Exception {
    return createFinancialMovements.create(financialMovementDtos);
  }

}