import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImportData } from '../../domain/models/import-data.model';
import { ImportFileUseCase } from '../../application/use-cases/import-file.usecase';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { LabelService } from '../../infrastructure/services/label.service';

@Component({
  selector: 'app-configuracion',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatSelectModule, FormsModule],
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {
  private importFileUseCase = inject(ImportFileUseCase);
  private http = inject(HttpClient);
  private labelService = inject(LabelService);

  importData: ImportData[] = [];
  displayedColumns: string[] = ['date', 'description', 'amount', 'label'];
  labels: string[] = [];

  ngOnInit() {
    this.labelService.getLabels().subscribe(labels => {
      this.labels = labels;
    });
  }

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

  onLabelChange(row: ImportData) {
    // Handle label change if needed (e.g., save to backend)
    console.log('Label changed for row:', row);
  }
}
