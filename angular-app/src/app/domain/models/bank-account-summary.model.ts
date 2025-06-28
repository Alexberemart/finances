import { BankAccount } from './bank-account.model';

export interface BankAccountSummary extends BankAccount {
  balance: number;
  currency: string;
}