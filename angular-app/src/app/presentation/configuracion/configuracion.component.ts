import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { ImportFileUseCase } from '../../application/use-cases/import-file.usecase';
import { LabelService } from '../../infrastructure/services/label.service';
import { SaveImportedMovementsUseCase } from '../../application/use-cases/save-imported-movements.usecase';
import { RegisterNotSkippedMovementsUseCase } from '../../application/use-cases/register-not-skipped-movements.usecase';
import { LoadDraftFinancialMovementsUseCase } from '../../application/use-cases/load-draft-financial-movements.usecase';

@Component({
  selector: 'app-configuracion',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatSelectModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule
  ],
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {
  private importFileUseCase = inject(ImportFileUseCase);
  private labelService = inject(LabelService);
  private saveImportedMovementsUseCase = inject(SaveImportedMovementsUseCase);
  private registerNotSkippedMovementsUseCase = inject(RegisterNotSkippedMovementsUseCase);
  private loadDraftFinancialMovementsUseCase = inject(LoadDraftFinancialMovementsUseCase);

  importData: ImportFinancialMovement[] = [];
  displayedColumns: string[] = ['skip', 'date', 'description', 'amount', 'label'];
  labels: string[] = [];
  selectedFileName: string | null = null;

  ngOnInit() {
    this.labelService.getLabels().subscribe(labels => {
      this.labels = labels;
    });
  }

  async onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    this.selectedFileName = file.name;
    try {
      this.importData = await this.importFileUseCase.importFileAndMapToFinancialMovements(file);
    } catch (error) {
      console.error('Error importing file:', error);
    }
  }

  async onIngFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    this.selectedFileName = file.name;
    try {
      // Implement ING file import logic here if needed
      console.log('ING file selected:', file);
    } catch (error) {
      console.error('Error importing ING file:', error);
    }
  }

  onLabelChange(row: ImportFinancialMovement) {
    // Handle label change if needed (e.g., save to backend)
    console.log('Label changed for row:', row);
  }

  async onSave() {
    const confirmed = window.confirm('¿Estás seguro de que quieres eliminar todos los movimientos previos y guardar los nuevos?');
    if (!confirmed) {
      return;
    }

    this.saveImportedMovementsUseCase.replaceAllDraftMovements(this.importData).subscribe({
      next: () => {
        alert('Movimientos guardados correctamente.');
      },
      error: (err) => {
        alert('Error al eliminar o guardar movimientos.');
        console.error(err);
      }
    });
  }

  onRegisterNotSkipped() {
    this.registerNotSkippedMovementsUseCase.execute(this.importData).subscribe({
      next: () => {
        alert('Movimientos no saltados registrados correctamente.');
      },
      error: (err) => {
        alert('Error al registrar movimientos no saltados.');
        console.error(err);
      }
    });
  }

  loadDraftMovements() {
    this.loadDraftFinancialMovementsUseCase.execute().subscribe({
      next: (movements) => {
        this.importData = movements;
        alert('Borradores cargados correctamente.');
      },
      error: (err) => {
        alert('Error al cargar borradores.');
        console.error(err);
      }
    });
  }

  get hasNotSkippedMovements(): boolean {
    return this.importData.some(m => !m.skip);
  }
}
