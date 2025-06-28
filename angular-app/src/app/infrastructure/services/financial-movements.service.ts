import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
// Update the import path if the file was moved, or create the file if missing
import { FinancialMovement } from '../../domain/models/financial-movement.model';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class FinancialMovementsService {
  private readonly saveUrl = `${environment.apiUrl}/financial-movements`;

  constructor(private http: HttpClient) {}

  saveFinancialMovements(movements: ImportFinancialMovement[]): Observable<any> {
    return this.http.post(this.saveUrl, movements);
  }

  getAllFinancialMovements(bankAccountId?: string): Observable<FinancialMovement[]> {
    // Mock data for demonstration
    const allMovements: FinancialMovement[] = [
      { id: 1, date: '2024-06-01', description: 'Ingreso BBVA', amount: 1000, label: 'ingreso', bankAccountId: 'bbva' },
      { id: 2, date: '2024-06-02', description: 'Gasto BBVA', amount: -200, label: 'gasto', bankAccountId: 'bbva' },
      { id: 3, date: '2024-06-03', description: 'Ingreso ING', amount: 1500, label: 'ingreso', bankAccountId: 'ing' },
      { id: 4, date: '2024-06-04', description: 'Gasto ING', amount: -300, label: 'gasto', bankAccountId: 'ing' }
    ];
    const filtered = bankAccountId
      ? allMovements.filter(m => m.bankAccountId === bankAccountId)
      : allMovements;
    return of(filtered);
  }
}