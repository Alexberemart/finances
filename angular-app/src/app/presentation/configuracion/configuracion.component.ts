import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { ImportFileUseCase } from '../../application/use-cases/import-file.usecase';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { LabelService } from '../../infrastructure/services/label.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ImportFinancialMovementsService } from '../../infrastructure/services/import-financial-movements.service';

@Component({
  selector: 'app-configuracion',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatSelectModule,
    FormsModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {
  private importFileUseCase = inject(ImportFileUseCase);
  private http = inject(HttpClient);
  private labelService = inject(LabelService);
  private importFinancialMovementsService = inject(ImportFinancialMovementsService);

  importData: ImportFinancialMovement[] = [];
  displayedColumns: string[] = ['date', 'description', 'amount', 'label'];
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
      this.importData = await this.importFileUseCase.importFile(file);
    } catch (error) {
      console.error('Error importing file:', error);
    }
  }

  onLabelChange(row: ImportFinancialMovement) {
    // Handle label change if needed (e.g., save to backend)
    console.log('Label changed for row:', row);
  }

  onSave() {
    this.importFinancialMovementsService.saveImportedMovements(this.importData).subscribe({
      next: () => {
        alert('Movimientos guardados correctamente.');
      },
      error: (err) => {
        alert('Error al guardar movimientos.');
        console.error(err);
      }
    });
  }
}
