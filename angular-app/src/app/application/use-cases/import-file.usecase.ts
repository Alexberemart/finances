import { Injectable, inject } from '@angular/core';
import { XlsxReaderService } from '../services/xlsx-reader.service';
import { mapCsvRowsToBBVAImportData } from '../../domain/adapters/csvrow-bbvaimportdata.adapter';
import { ImportRestService } from '../../infrastructure/services/import-rest.service';
import { ImportData } from '../../domain/models/import-data.model';

@Injectable({ providedIn: 'root' })
export class ImportFileUseCase {
  private xlsxReader = inject(XlsxReaderService);
  private importRest = inject(ImportRestService);


  async importFile(file: File): Promise<ImportData[]> {
    const csvRows = await this.xlsxReader.readXlsxFile(file);
    const bbvaData = mapCsvRowsToBBVAImportData(csvRows);
    return this.importRest.sendImport(bbvaData);
  }
}