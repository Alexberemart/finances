import { Injectable } from '@angular/core';
import { Observable, forkJoin, map, switchMap } from 'rxjs';
import { BankAccountSummary } from '../../domain/models/bank-account-summary.model';
import { BankAccountService } from '../../infrastructure/services/bank-account.service';
import { BankAccount } from '../../domain/models/bank-account.model';
import { GetFinancialMovementsSummaryUseCase } from './get-financial-movements-summary.usecase';

@Injectable({ providedIn: 'root' })
export class GetBankAccountSummariesUseCase {
  constructor(
    private bankAccountService: BankAccountService,
    private getFinancialMovementsSummaryUseCase: GetFinancialMovementsSummaryUseCase
  ) {}

  execute(): Observable<BankAccountSummary[]> {
    return this.bankAccountService.getBankAccounts().pipe(
      switchMap((accounts: BankAccount[]) =>
        forkJoin(
          accounts.map(account =>
            this.getFinancialMovementsSummaryUseCase.execute(account.id).pipe(
              map(summary => ({
                id: account.id,
                name: account.name,
                balance: summary.total,
                currency: 'EUR'
              }))
            )
          )
        )
      )
    );
  }
}