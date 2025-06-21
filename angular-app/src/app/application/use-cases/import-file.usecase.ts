import { Injectable, inject } from '@angular/core';
import { XlsxReaderService } from '../services/xlsx-reader.service';
import { mapCsvRowsToBBVAImportData } from '../../domain/adapters/csvrow-bbvaimportdata.adapter';
import { ImportFinancialMovementsService } from '../../infrastructure/services/import-financial-movements.service';
import { ImportFinancialMovement } from '../../domain/models/import-financial-movement.model';

@Injectable({ providedIn: 'root' })
export class ImportFileUseCase {
  private xlsxReader = inject(XlsxReaderService);
  private importFinancialMovementsService = inject(ImportFinancialMovementsService);


  async importFile(file: File): Promise<ImportFinancialMovement[]> {
    const csvRows = await this.xlsxReader.readXlsxFile(file);
    const bbvaData = mapCsvRowsToBBVAImportData(csvRows);
    return this.importFinancialMovementsService.createImportMovements(bbvaData);
  }
}