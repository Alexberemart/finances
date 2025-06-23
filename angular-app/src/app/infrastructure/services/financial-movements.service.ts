import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class FinancialMovementsService {
  private readonly saveUrl = `${environment.apiUrl}/financial-movements`;

  constructor(private http: HttpClient) {}

  saveFinancialMovements(movements: ImportFinancialMovement[]): Observable<any> {
    return this.http.post(this.saveUrl, movements);
  }
}