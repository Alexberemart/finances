import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ImportData } from '../../domain/models/import-data.model';
import { BBVAImportData } from '../../domain/models/bbva-import-data.model';
import { firstValueFrom } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ImportRestService {
  // Use full URL to call backend on localhost:8080
  private readonly apiUrl = 'http://localhost:8080/financial-movements';

  constructor(private http: HttpClient) {}

  sendImport(bbvaData: BBVAImportData[]): Promise<ImportData[]> {
    // Call the backend endpoint (newEmployee in FinancialMovementController)
    return firstValueFrom(
      this.http.post<ImportData[]>(this.apiUrl, bbvaData)
    );
  }
}