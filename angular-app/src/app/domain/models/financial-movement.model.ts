import { BankAccount } from './bank-account.model';

export interface FinancialMovement {
  id: number;
  date: string;
  description: string;
  amount: number;
  label: string;
  bankAccount: BankAccount;
}