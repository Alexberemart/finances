import { Injectable } from '@angular/core';
import * as XLSX from 'xlsx';
import { CsvRow } from '../models/csv-row.model';

@Injectable({
  providedIn: 'root'
})
export class XlsxReaderService {
  readXlsxFile(file: File): Promise<CsvRow[]> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = () => {
        try {
          const data = new Uint8Array(reader.result as ArrayBuffer);
          const workbook = XLSX.read(data, { type: 'array' });
          const sheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[sheetName];
          const json: any[] = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

          // Assume first row is header
          const [header, ...rows] = json;
          const csvRows: CsvRow[] = rows
            .filter(row => row.length >= 3)
            .map(row => ({
              date: row[0] ?? '',
              description: row[1] ?? '',
              amount: Number(row[2])
            }));
          resolve(csvRows);
        } catch (err) {
          reject(err);
        }
      };
      reader.onerror = reject;
      reader.readAsArrayBuffer(file);
    });
  }
}