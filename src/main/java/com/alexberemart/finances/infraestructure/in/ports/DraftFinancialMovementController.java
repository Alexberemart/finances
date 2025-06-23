package com.alexberemart.finances.infraestructure.in.ports;

import com.alexberemart.finances.application.usecases.CreateDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.DeleteAllDraftFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DraftFinancialMovementController {

  private final CreateDraftFinancialMovements createDraftFinancialMovements;
  private final SaveDraftFinancialMovements saveDraftFinancialMovements;
  private final DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements;

  DraftFinancialMovementController(
      CreateDraftFinancialMovements createDraftFinancialMovements,
      SaveDraftFinancialMovements saveDraftFinancialMovements,
      DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements) {
    this.createDraftFinancialMovements = createDraftFinancialMovements;
    this.saveDraftFinancialMovements = saveDraftFinancialMovements;
    this.deleteAllDraftFinancialMovements = deleteAllDraftFinancialMovements;
  }

  @PostMapping("/api/draft-financial-movements/create")
  List<ImportMovementDto> createDraftFinancialMovements(@RequestBody List<FinancialMovementDto> financialMovementDtos)
      throws Exception {
    return createDraftFinancialMovements.create(financialMovementDtos);
  }

  @PostMapping("/api/draft-financial-movements/save")
  void saveDraftFinancialMovements(@RequestBody List<DraftFinancialMovementDto> draftFinancialMovementDtos) {
    saveDraftFinancialMovements.save(draftFinancialMovementDtos);
  }

  @DeleteMapping("/api/draft-financial-movements")
  void deleteAllDraftFinancialMovements() {
    deleteAllDraftFinancialMovements.deleteAll();
  }
}