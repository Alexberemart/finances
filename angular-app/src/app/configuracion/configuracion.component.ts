import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BBVAImportData } from '../models/bbva-import-data.model';
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
  bbvaData: BBVAImportData[] = [];
  displayedColumns: string[] = ['date', 'description', 'amount'];

  constructor(private xlsxReader: XlsxReaderService) {}

  async onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    try {
      this.bbvaData = await this.xlsxReader.readXlsxFile(file);
    } catch (error) {
      console.error('Error reading file:', error);
    }
  }
}
