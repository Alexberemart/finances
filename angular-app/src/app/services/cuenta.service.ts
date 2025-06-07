import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {
  getSaldo(): Observable<number> {
    // Mocked value, replace with real HTTP call later
    return of(12345.67);
  }
}