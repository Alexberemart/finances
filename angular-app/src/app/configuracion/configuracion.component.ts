import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CsvRow } from '../models/csv-row.model';
import { BBVAImportData } from '../models/bbva-import-data.model';
import { csvRowArrayToBBVAImportDataArray } from '../adapters/csvrow-to-bbvaimportdata.adapter';
import { XlsxReaderService } from '../services/xlsx-reader.service';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-configuracion',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent {
  csvRows: CsvRow[] = [];
  bbvaData: BBVAImportData[] = [];
  displayedColumns: string[] = ['date', 'description', 'amount'];

  constructor(private xlsxReader: XlsxReaderService) {}

  async onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    try {
      this.csvRows = await this.xlsxReader.readXlsxFile(file);
      this.bbvaData = csvRowArrayToBBVAImportDataArray(this.csvRows);
    } catch (error) {
      // Handle error (show message, etc.)
      console.error('Error reading file:', error);
    }
  }
}
