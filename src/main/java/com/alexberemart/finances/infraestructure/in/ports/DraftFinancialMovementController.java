package com.alexberemart.finances.infraestructure.in.ports;

import com.alexberemart.finances.application.usecases.CreateDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.DeleteAllDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.GetAllDraftFinancialMovements;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;
import com.alexberemart.finances.infraestructure.services.DraftFinancialMovementTransactionalService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
class DraftFinancialMovementController {

  private final CreateDraftFinancialMovements createDraftFinancialMovements;
  private final SaveDraftFinancialMovements saveDraftFinancialMovements;
  private final DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements;
  private final GetAllDraftFinancialMovements getAllDraftFinancialMovements;
  private final DraftFinancialMovementTransactionalService transactionalService;

  DraftFinancialMovementController(
      CreateDraftFinancialMovements createDraftFinancialMovements,
      SaveDraftFinancialMovements saveDraftFinancialMovements,
      DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements,
      GetAllDraftFinancialMovements getAllDraftFinancialMovements,
      DraftFinancialMovementTransactionalService transactionalService) {
    this.createDraftFinancialMovements = createDraftFinancialMovements;
    this.saveDraftFinancialMovements = saveDraftFinancialMovements;
    this.deleteAllDraftFinancialMovements = deleteAllDraftFinancialMovements;
    this.getAllDraftFinancialMovements = getAllDraftFinancialMovements;
    this.transactionalService = transactionalService;
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

  @PostMapping("/api/draft-financial-movements/replace-all")
  public ResponseEntity<Void> replaceAllDraftFinancialMovements(@RequestBody List<DraftFinancialMovementDto> newMovements) {
      transactionalService.replaceAllDraftFinancialMovements(newMovements);
      return ResponseEntity.ok().build();
  }

  @GetMapping("/api/draft-financial-movements")
  public List<DraftFinancialMovementDto> getAllDraftFinancialMovements() {
      return getAllDraftFinancialMovements.execute();
  }
}