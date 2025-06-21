import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LabelService {
  private readonly apiUrl = 'YOUR_LABELS_API_URL';

  constructor(private http: HttpClient) {}

  getLabels(): Observable<string[]> {
    // Mocked categories for income bank movements
    return of([
      'Salary',
      'Interest',
      'Dividends',
      'Refund',
      'Other Income'
    ]);
  }
}