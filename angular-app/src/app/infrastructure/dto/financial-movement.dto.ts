export interface FinancialMovementDto {
  id: number;
  date: string;
  description: string;
  amount: number;
  label: string;
  bankAccountId: string;
}