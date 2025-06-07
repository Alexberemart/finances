import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { BankAccount } from '../../models/bank-account.model'; // <-- FIXED

@Injectable({
  providedIn: 'root'
})
export class CuentaService {
  getBankAccount(): Observable<BankAccount> {
    // Mocked value, replace with real HTTP call later
    return of({
      id: '1',
      name: 'Cuenta principal',
      balance: 12345.67,
      currency: 'EUR'
    });
  }
}