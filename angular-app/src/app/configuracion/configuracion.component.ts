import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as XLSX from 'xlsx';
import { CsvRow } from '../models/csv-row.model';
import { BBVAImportData } from '../models/bbva-import-data.model';

@Component({
  selector: 'app-configuracion',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent {
  csvRows: CsvRow[] = [];
  bbvaData: BBVAImportData[] = [];

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      const data = new Uint8Array(reader.result as ArrayBuffer);
      const workbook = XLSX.read(data, { type: 'array' });
      const sheetName = workbook.SheetNames[0];
      const worksheet = workbook.Sheets[sheetName];
      const json: any[] = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

      // Assume first row is header
      const [header, ...rows] = json;
      this.csvRows = rows
        .filter(row => row.length >= 3)
        .map(row => ({
          date: row[0] ?? '',
          description: row[1] ?? '',
          amount: Number(row[2])
        }));

      // Map CsvRow[] to BBVAImportData[]
      this.bbvaData = this.csvRows.map(row => ({
        date: row.date,
        description: row.description,
        amount: row.amount
      }));
    };
    reader.readAsArrayBuffer(file);
  }
}
