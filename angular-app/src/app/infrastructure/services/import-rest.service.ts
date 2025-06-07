import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ImportData } from '../../domain/models/import-data.model';
import { BBVAImportData } from '../../domain/models/bbva-import-data.model';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ImportRestService {
  private readonly apiUrl = `${environment.apiUrl}/financial-movements/import`;

  constructor(private http: HttpClient) {}

  sendImport(bbvaData: BBVAImportData[]): Promise<ImportData[]> {
    return firstValueFrom(
      this.http.post<ImportData[]>(this.apiUrl, bbvaData)
    );
  }
}