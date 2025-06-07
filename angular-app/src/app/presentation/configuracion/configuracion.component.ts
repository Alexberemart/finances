import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImportData } from '../../domain/models/import-data.model';
import { ImportFileUseCase } from '../../application/use-cases/import-file.usecase';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-configuracion',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent {
  importData: ImportData[] = [];
  displayedColumns: string[] = ['date', 'description', 'amount'];

  constructor(private importFileUseCase: ImportFileUseCase) {}

  async onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    try {
      this.importData = await this.importFileUseCase.importFile(file);
    } catch (error) {
      console.error('Error importing file:', error);
    }
  }
}
