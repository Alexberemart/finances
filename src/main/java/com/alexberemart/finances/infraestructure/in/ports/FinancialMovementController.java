package com.alexberemart.finances.infraestructure.in.ports;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;
import com.alexberemart.finances.domain.useCases.CreateFinancialMovements;

@RestController
class FinancialMovementController {

  private final CreateFinancialMovements createFinancialMovements;

  FinancialMovementController(CreateFinancialMovements createFinancialMovements) {
    this.createFinancialMovements = createFinancialMovements;
  }

  @PostMapping("/api/financial-movements/import")
  List<ImportMovementDto> importFinancialMovements(@RequestBody List<FinancialMovementDto> financialMovementDtos)
      throws Exception {
    return createFinancialMovements.create(financialMovementDtos);
  }

}