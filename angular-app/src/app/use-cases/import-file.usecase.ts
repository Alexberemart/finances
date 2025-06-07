import { Injectable } from '@angular/core';
import { XlsxReaderService } from '../services/xlsx-reader.service';
import { ImportRestService } from '../services/import-rest.service';
import { ImportData } from '../models/import-data.model';

@Injectable({ providedIn: 'root' })
export class ImportFileUseCase {
  constructor(
    private xlsxReader: XlsxReaderService,
    private importRest: ImportRestService
  ) {}

  async importFile(file: File): Promise<ImportData[]> {
    const bbvaData = await this.xlsxReader.readXlsxFile(file);
    return this.importRest.sendImport(bbvaData);
  }
}