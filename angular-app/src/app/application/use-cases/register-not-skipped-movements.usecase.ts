import { Injectable } from '@angular/core';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { Observable, throwError } from 'rxjs';
import { FinancialMovementsService } from '../../infrastructure/services/financial-movements.service';

@Injectable({ providedIn: 'root' })
export class RegisterNotSkippedMovementsUseCase {
  constructor(private service: FinancialMovementsService) {}

  execute(movements: ImportFinancialMovement[]): Observable<any> {
    const notSkipped = movements.filter(m => !m.skip);

    // Validate required fields
    const invalid = notSkipped.filter(m =>
      !m.bankAccountId || !m.label || !m.type
    );
    if (invalid.length > 0) {
      return throwError(() => new Error('Todos los movimientos deben tener cuenta bancaria, etiqueta y tipo.'));
    }

    return this.service.saveFinancialMovements(notSkipped);
  }
}