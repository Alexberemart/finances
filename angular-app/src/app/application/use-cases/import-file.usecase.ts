import { Injectable, inject } from '@angular/core';
import { XlsxReaderService } from '../services/xlsx-reader.service';
import { mapCsvRowsToBBVAImportData } from '../../domain/adapters/csvrow-bbvaimportdata.adapter';
import { ImportFinancialMovementsService } from '../../infrastructure/services/import-financial-movements.service';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';
import { ImportedMovementRaw } from '../../domain/models/imported-movement-raw.model';
import { BBVAImportData } from '../../domain/models/bbva-import-data.model';

@Injectable({ providedIn: 'root' })
export class ImportFileUseCase {
  private xlsxReader = inject(XlsxReaderService);
  private importFinancialMovementsService = inject(ImportFinancialMovementsService);


  async importFileAndMapToFinancialMovements(file: File): Promise<ImportFinancialMovement[]> {
    const csvRows = await this.xlsxReader.readXlsxFile(file);
    const bbvaData: BBVAImportData[] = mapCsvRowsToBBVAImportData(csvRows);
    const rawMovements: ImportedMovementRaw[] = await this.importFinancialMovementsService.createImportMovements(bbvaData);
    return rawMovements.map(m => ({
      ...m,
      label: '' // or undefined, or prefill as needed
    }));
  }
}