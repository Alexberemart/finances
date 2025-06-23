package com.alexberemart.finances.infraestructure.in.ports;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveImportFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;

@RestController
class FinancialMovementController {

  private final CreateFinancialMovements createFinancialMovements;
  private final SaveImportFinancialMovements saveImportFinancialMovements;
  private final SaveFinancialMovements saveFinancialMovements;

  FinancialMovementController(
      CreateFinancialMovements createFinancialMovements,
      SaveImportFinancialMovements saveImportFinancialMovements,
      SaveFinancialMovements saveFinancialMovements) {
    this.createFinancialMovements = createFinancialMovements;
    this.saveImportFinancialMovements = saveImportFinancialMovements;
    this.saveFinancialMovements = saveFinancialMovements;
  }

  @PostMapping("/api/financial-movements/import")
  List<ImportMovementDto> importFinancialMovements(@RequestBody List<FinancialMovementDto> financialMovementDtos)
      throws Exception {
    return createFinancialMovements.create(financialMovementDtos);
  }

  @PostMapping("/api/financial-movements/save")
  void saveImportFinancialMovements(@RequestBody List<ImportFinancialMovementDto> importFinancialMovements) {
    saveImportFinancialMovements.save(importFinancialMovements);
  }

  @PostMapping("/api/financial-movements")
  void saveFinancialMovements(@RequestBody List<ImportFinancialMovementDto> importFinancialMovements) {
    saveFinancialMovements.save(importFinancialMovements);
  }
}