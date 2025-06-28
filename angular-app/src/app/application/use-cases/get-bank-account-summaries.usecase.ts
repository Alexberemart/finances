import { Injectable } from '@angular/core';
import { Observable, of, map } from 'rxjs';
import { BankAccountSummary } from '../../domain/models/bank-account-summary.model';
import { BankAccountService } from '../../infrastructure/services/bank-account.service';
import { BankAccount } from '../../domain/models/bank-account.model';

@Injectable({ providedIn: 'root' })
export class GetBankAccountSummariesUseCase {
  constructor(private bankAccountService: BankAccountService) {}

  execute(): Observable<BankAccountSummary[]> {
    return this.bankAccountService.getBankAccounts().pipe(
      map((accounts: BankAccount[]) =>
        accounts.map(account => ({
          id: account.id,
          name: account.name,
          // Mock values for balance and currency; replace with real logic as needed
          balance: account.id === 'bbva' ? 1234.56 : 7890.12,
          currency: 'EUR'
        }))
      )
    );
  }
}