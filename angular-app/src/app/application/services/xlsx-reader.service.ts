import { Injectable } from '@angular/core';
import * as XLSX from 'xlsx';
import { CsvRow } from '../../domain/models/csv-row.model';

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
          const json: string[][] = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

          // 1. Skip first 4 rows, use 5th as header
          const headerRow = json[4];
          const dataRows = json.slice(5);

          // 2. Find indexes for the columns
          const fechaIdx = headerRow.findIndex((h: string) => h?.toLowerCase() === 'fecha');
          const conceptoIdx = headerRow.findIndex((h: string) => h?.toLowerCase() === 'concepto');
          const importeIdx = headerRow.findIndex((h: string) => h?.toLowerCase() === 'importe');
          const movimientoIdx = headerRow.findIndex((h: string) => h?.toLowerCase() === 'movimiento');
          const observacionesIdx = headerRow.findIndex((h: string) => h?.toLowerCase() === 'observaciones');

          // 3. Map to CsvRow[] (including new fields)
          const csvRows: CsvRow[] = dataRows
            .filter(row => row.length > Math.max(fechaIdx, conceptoIdx, importeIdx))
            .map(row => ({
              date: row[fechaIdx] ?? '',
              description: row[conceptoIdx] ?? '',
              amount: Number(row[importeIdx]),
              movimiento: movimientoIdx !== -1 ? row[movimientoIdx] ?? '' : '',
              observaciones: observacionesIdx !== -1 ? row[observacionesIdx] ?? '' : ''
            }));

          // 4. Return CsvRow[]
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