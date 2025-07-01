import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { FinancialMovementsService } from '../../infrastructure/services/financial-movements.service';
import { FinancialMovement } from '../../domain/models/financial-movement.model';

@Injectable({ providedIn: 'root' })
export class GetFinancialMovementsSummaryUseCase {
  constructor(private service: FinancialMovementsService) {}

  execute(bankAccountId: string): Observable<{ movements: FinancialMovement[]; total: number }> {
    return this.service.getAllFinancialMovements(bankAccountId).pipe(
      map(movements => ({
        movements,
        total: movements.reduce((sum, m) => sum + m.amount, 0)
      }))
    );
  }
}