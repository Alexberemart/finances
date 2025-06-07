import { Injectable } from '@angular/core';
import { ImportData } from '../models/import-data.model';
import { BBVAImportData } from '../models/bbva-import-data.model';

@Injectable({ providedIn: 'root' })
export class ImportRestService {
  // Mock: just map BBVAImportData to ImportData
  sendImport(bbvaData: BBVAImportData[]): Promise<ImportData[]> {
    return new Promise(resolve => {
      // Simulate REST delay
      setTimeout(() => {
        resolve(bbvaData.map(item => ({
          date: item.date,
          description: item.description,
          amount: item.amount
        })));
      }, 500);
    });
  }
}