import { Injectable } from '@angular/core';
import { ImportFinancialMovementsService } from '../../infrastructure/services/import-financial-movements.service';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class LoadDraftFinancialMovementsUseCase {
  constructor(private service: ImportFinancialMovementsService) {}

  execute(): Observable<ImportFinancialMovement[]> {
    return this.service.getAllDraftMovements();
  }
}