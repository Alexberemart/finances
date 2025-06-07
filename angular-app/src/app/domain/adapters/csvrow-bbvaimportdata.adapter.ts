import { CsvRow } from '../models/csv-row.model';
import { BBVAImportData } from '../models/bbva-import-data.model';
import { parse, format } from 'date-fns';

function convertToIsoDate(dateStr: string): string {
  // Safely parse dd/MM/yyyy and format as yyyy-MM-dd
  const parsed = parse(dateStr, 'dd/MM/yyyy', new Date());
  return isNaN(parsed.getTime()) ? dateStr : format(parsed, 'yyyy-MM-dd');
}

export function mapCsvRowsToBBVAImportData(rows: CsvRow[]): BBVAImportData[] {
  return rows.map(row => ({
    date: convertToIsoDate(row.date),
    description: row.description,
    amount: row.amount,
    movimiento: row.movimiento,
    observaciones: row.observaciones
  }));
}