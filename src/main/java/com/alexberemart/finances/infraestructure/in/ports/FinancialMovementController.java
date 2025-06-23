package com.alexberemart.finances.infraestructure.in.ports;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;

@RestController
class FinancialMovementController {

  private final CreateFinancialMovements createFinancialMovements;

  FinancialMovementController(CreateFinancialMovements createFinancialMovements) {
    this.createFinancialMovements = createFinancialMovements;
  }

  @PostMapping("/api/financial-movements")
  void saveFinancialMovements(@RequestBody List<ImportFinancialMovementDto> importFinancialMovements) {
    createFinancialMovements.create(importFinancialMovements);
  }
}