import { Injectable } from '@angular/core';
import { XlsxReaderService } from '../services/xlsx-reader.service';
import { mapCsvRowsToBBVAImportData } from '../../domain/adapters/csvrow-bbvaimportdata.adapter';
import { ImportRestService } from '../../infrastructure/services/import-rest.service';
import { ImportData } from '../../domain/models/import-data.model';

@Injectable({ providedIn: 'root' })
export class ImportFileUseCase {
  constructor(
    private xlsxReader: XlsxReaderService,
    private importRest: ImportRestService
  ) {}

  async importFile(file: File): Promise<ImportData[]> {
    const csvRows = await this.xlsxReader.readXlsxFile(file);
    const bbvaData = mapCsvRowsToBBVAImportData(csvRows);
    console.log('bbvaData:', bbvaData); // <-- Debug output
    return this.importRest.sendImport(bbvaData);
  }
}