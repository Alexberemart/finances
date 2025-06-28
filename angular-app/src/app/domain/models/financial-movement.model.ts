export interface FinancialMovement {
  id: number;
  date: string;
  description: string;
  amount: number;
  label: string;
  bankAccountId: string;
}