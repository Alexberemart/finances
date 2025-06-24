import { Injectable } from '@angular/core';
import { ImportFinancialMovementsService } from '../../infrastructure/services/import-financial-movements.service';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class SaveImportedMovementsUseCase {
  constructor(private service: ImportFinancialMovementsService) {}

  replaceAllDraftMovements(movements: ImportFinancialMovement[]): Observable<any> {
    return this.service.replaceAllDraftMovements(movements);
  }
}