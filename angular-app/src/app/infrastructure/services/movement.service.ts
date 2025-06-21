// Example in src/app/infrastructure/services/movement.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ImportData } from '../../domain/models/import-data.model';

@Injectable({ providedIn: 'root' })
export class MovementService {
  private readonly apiUrl = 'YOUR_SAVE_API_URL';

  constructor(private http: HttpClient) {}

  saveMovements(movements: ImportData[]): Observable<any> {
    return this.http.post(this.apiUrl, movements);
  }
}