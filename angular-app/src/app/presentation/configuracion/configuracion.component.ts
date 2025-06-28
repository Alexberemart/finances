import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatSnackBar } from '@angular/material/snack-bar';

import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { ImportFileUseCase } from '../../application/use-cases/import-file.usecase';
import { LabelService } from '../../infrastructure/services/label.service';
import { SaveImportedMovementsUseCase } from '../../application/use-cases/save-imported-movements.usecase';
import { RegisterNotSkippedMovementsUseCase } from '../../application/use-cases/register-not-skipped-movements.usecase';
import { LoadDraftFinancialMovementsUseCase } from '../../application/use-cases/load-draft-financial-movements.usecase';
import { BankAccount } from '../../domain/models/bank-account.model';
import { BankAccountService } from '../../infrastructure/services/bank-account.service';

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
    MatCheckboxModule,
    MatCardModule,
    MatDividerModule
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
  private bankAccountService = inject(BankAccountService);
  private snackBar = inject(MatSnackBar);

  importData: ImportFinancialMovement[] = [];
  displayedColumns: string[] = ['skip', 'date', 'description', 'amount', 'label', 'type', 'bankAccount'];
  labels: string[] = [];
  selectedFileName: string | null = null;
  isDraftDirty = false; // <-- Add this line
  bankAccounts: BankAccount[] = [];

  ngOnInit() {
    this.labelService.getLabels().subscribe(labels => {
      this.labels = labels;
    });
    this.bankAccountService.getBankAccounts().subscribe(accounts => {
      this.bankAccounts = accounts;
    });
  }

  async onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    this.selectedFileName = file.name;
    try {
      this.importData = await this.importFileUseCase.importFileAndMapToFinancialMovements(file);
      this.isDraftDirty = true; // Mark as dirty
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
    this.isDraftDirty = true; // Mark as dirty
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
        this.snackBar.open('Movimientos guardados correctamente.', 'Cerrar', {
          duration: 3000,
          panelClass: ['snackbar-success']
        });
        this.isDraftDirty = false; // Reset dirty flag after saving
      },
      error: (err) => {
        this.snackBar.open('Error al eliminar o guardar movimientos.', 'Cerrar', {
          duration: 3000,
          panelClass: ['snackbar-error']
        });
        console.error(err);
      }
    });
  }

  onRegisterNotSkipped() {
    this.registerNotSkippedMovementsUseCase.execute(this.importData).subscribe({
      next: () => {
        this.snackBar.open('Movimientos no saltados registrados correctamente.', 'Cerrar', { duration: 3000 });
      },
      error: (err) => {
        this.snackBar.open(err.message, 'Cerrar', { duration: 500000, panelClass: ['snackbar-error'] });
        console.error(err);
      }
    });
  }

  loadDraftMovements() {
    this.loadDraftFinancialMovementsUseCase.execute().subscribe({
      next: (movements) => {
        this.importData = movements;
        this.isDraftDirty = false; // Reset dirty flag after loading
        this.snackBar.open('Borradores cargados correctamente.', 'Cerrar', {
          duration: 3000,
        });
      },
      error: (err) => {
        this.snackBar.open('Error al cargar borradores.', 'Cerrar', {
          duration: 3000,
        });
        console.error(err);
      }
    });
  }

  get hasNotSkippedMovements(): boolean {
    return this.importData.some(m => !m.skip);
  }

  // Also update when toggling skip checkbox:
  onSkipChange() {
    this.isDraftDirty = true;
  }

  deleteAllDraftMovements() {
    // Implement your logic here, for example:
    this.importData = [];
    this.isDraftDirty = false;
    // Optionally, call a use case/service to delete from backend if needed
    // this.deleteDraftFinancialMovementsUseCase.execute().subscribe();
  }

  onBankAccountChange(row: ImportFinancialMovement) {
    this.isDraftDirty = true;
    // Optionally handle logic when bank account changes
  }

  onTypeChange(row: ImportFinancialMovement) {
    this.isDraftDirty = true;
    // Optionally handle logic when type changes
  }
}
