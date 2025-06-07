import { CsvRow } from '../models/csv-row.model';
import { BBVAImportData } from '../models/bbva-import-data.model';

export function mapCsvRowsToBBVAImportData(rows: CsvRow[]): BBVAImportData[] {
  return rows.map(row => ({
    date: row.date,
    description: row.description,
    amount: row.amount
  }));
}