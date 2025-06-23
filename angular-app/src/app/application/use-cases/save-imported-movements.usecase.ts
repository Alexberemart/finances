import { Injectable } from '@angular/core';
import { ImportFinancialMovementsService } from '../../infrastructure/services/import-financial-movements.service';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class SaveImportedMovementsUseCase {
  constructor(private service: ImportFinancialMovementsService) {}

  execute(movements: ImportFinancialMovement[]): Observable<any> {
    const toRegister = movements.filter(m => !m.skip);
    return this.service.saveImportedMovements(toRegister);
  }
}