package com.alexberemart.finances.infraestructure.in.ports;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.application.usecases.GetFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.models.FinancialMovement;

@RestController
class FinancialMovementController {

  private final CreateFinancialMovements createFinancialMovements;
  private final GetFinancialMovements getFinancialMovements;

  @Autowired
  FinancialMovementController(
      CreateFinancialMovements createFinancialMovements,
      GetFinancialMovements getFinancialMovements) {
    this.createFinancialMovements = createFinancialMovements;
    this.getFinancialMovements = getFinancialMovements;
  }

  @PostMapping("/api/financial-movements")
  void saveFinancialMovements(@RequestBody List<DraftFinancialMovementDto> draftFinancialMovements) {
    createFinancialMovements.create(draftFinancialMovements);
  }

  @GetMapping("/api/financial-movements")
  List<FinancialMovement> getFinancialMovements(@RequestParam String bankAccountId) {
    return getFinancialMovements.findByBankAccountId(bankAccountId);
  }
}