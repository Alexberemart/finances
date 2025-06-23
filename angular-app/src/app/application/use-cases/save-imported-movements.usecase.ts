import { Injectable } from '@angular/core';
import { ImportFinancialMovementsService } from '../../infrastructure/services/import-financial-movements.service';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class SaveImportedMovementsUseCase {
  constructor(private service: ImportFinancialMovementsService) {}

  execute(movements: ImportFinancialMovement[]): Observable<any> {
    // Save all movements, regardless of skip flag
    return this.service.saveImportedMovements(movements);
  }
}