import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { FinancialMovement } from '../../domain/models/financial-movement.model';
import { FinancialMovementDto } from '../dto/financial-movement.dto';
import { BankAccount } from '../../domain/models/bank-account.model';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class FinancialMovementsService {
  private readonly saveUrl = `${environment.apiUrl}/financial-movements`;

  constructor(private http: HttpClient) {}

  saveFinancialMovements(movements: ImportFinancialMovement[]): Observable<any> {
    return this.http.post(this.saveUrl, movements);
  }

  getAllFinancialMovements(bankAccountId: string): Observable<FinancialMovement[]> {
    const params = new HttpParams().set('bankAccountId', bankAccountId);
    return this.http.get<FinancialMovementDto[]>(this.saveUrl, { params }).pipe(
      map(dtoList => dtoList.map(dto => this.mapDtoToModel(dto)))
    );
  }

  private mapDtoToModel(dto: FinancialMovementDto): FinancialMovement {
    return {
      id: dto.id,
      date: dto.date,
      description: dto.description,
      amount: dto.amount,
      label: dto.label,
      bankAccount: { id: dto.bankAccountId, name: '' } // Fill name if available
    };
  }
}