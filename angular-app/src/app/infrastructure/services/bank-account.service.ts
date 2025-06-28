import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { BankAccount } from '../../domain/models/bank-account.model';

@Injectable({ providedIn: 'root' })
export class BankAccountService {
  getBankAccounts(): Observable<BankAccount[]> {
    return of([
      { id: 'bbva', name: 'BBVA' },
      { id: 'ing', name: 'ING' }
    ]);
  }
}