import { Injectable } from '@angular/core';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { Observable } from 'rxjs';
import { FinancialMovementsService } from '../../infrastructure/services/financial-movements.service';

@Injectable({ providedIn: 'root' })
export class RegisterNotSkippedMovementsUseCase {
  constructor(private service: FinancialMovementsService) {}

  execute(movements: ImportFinancialMovement[]): Observable<any> {
    const notSkipped = movements.filter(m => !m.skip);
    return this.service.saveFinancialMovements(notSkipped);
  }
}