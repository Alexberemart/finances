package com.alexberemart.finances.infraestructure.in.ports;

import com.alexberemart.finances.application.usecases.CreateDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveImportFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DraftFinancialMovementController {

  private final CreateDraftFinancialMovements createDraftFinancialMovements;
  private final SaveImportFinancialMovements saveImportFinancialMovements;

  DraftFinancialMovementController(
      CreateDraftFinancialMovements createDraftFinancialMovements,
      SaveImportFinancialMovements saveImportFinancialMovements) {
    this.createDraftFinancialMovements = createDraftFinancialMovements;
    this.saveImportFinancialMovements = saveImportFinancialMovements;
  }

  @PostMapping("/api/financial-movements/import")
  List<ImportMovementDto> importFinancialMovements(@RequestBody List<FinancialMovementDto> financialMovementDtos)
      throws Exception {
    return createDraftFinancialMovements.create(financialMovementDtos);
  }

  @PostMapping("/api/financial-movements/save")
  void saveImportFinancialMovements(@RequestBody List<ImportFinancialMovementDto> importFinancialMovements) {
    saveImportFinancialMovements.save(importFinancialMovements);
  }
}