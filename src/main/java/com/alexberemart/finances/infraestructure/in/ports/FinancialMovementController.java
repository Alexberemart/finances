package com.alexberemart.finances.infraestructure.in.ports;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;

@RestController
class FinancialMovementController {

  private final CreateFinancialMovements createFinancialMovements;
  // Add your new use case here when implemented
  // private final SaveImportFinancialMovements saveImportFinancialMovements;

  FinancialMovementController(CreateFinancialMovements createFinancialMovements /*, SaveImportFinancialMovements saveImportFinancialMovements */) {
    this.createFinancialMovements = createFinancialMovements;
    // this.saveImportFinancialMovements = saveImportFinancialMovements;
  }

  @PostMapping("/api/financial-movements/import")
  List<ImportMovementDto> importFinancialMovements(@RequestBody List<FinancialMovementDto> financialMovementDtos)
      throws Exception {
    return createFinancialMovements.create(financialMovementDtos);
  }

  @PostMapping("/api/financial-movements/save")
  void saveImportFinancialMovements(@RequestBody List<ImportFinancialMovementDto> importFinancialMovements) {
    // TODO: Call your new use case here when implemented
    // saveImportFinancialMovements.save(importFinancialMovements);
  }
}