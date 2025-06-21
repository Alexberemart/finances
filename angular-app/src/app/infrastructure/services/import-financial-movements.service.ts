import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ImportedMovementRaw } from '../../domain/models/imported-movement-raw.model';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { BBVAImportData } from '../../domain/models/bbva-import-data.model';
import { firstValueFrom, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ImportFinancialMovementsService {
  private http = inject(HttpClient);

  private readonly createUrl = `${environment.apiUrl}/financial-movements/import`;
  private readonly saveUrl = `${environment.apiUrl}/financial-movements/save`;

  // Returns raw movements (no label)
  createImportMovements(bbvaData: BBVAImportData[]): Promise<ImportedMovementRaw[]> {
    return firstValueFrom(
      this.http.post<ImportedMovementRaw[]>(this.createUrl, bbvaData)
    );
  }

  // Saves movements with label
  saveImportedMovements(movements: ImportFinancialMovement[]): Observable<any> {
    return this.http.post(this.saveUrl, movements);
  }
}