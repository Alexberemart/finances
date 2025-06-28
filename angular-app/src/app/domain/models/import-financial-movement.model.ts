export interface ImportFinancialMovement {
  date: Date;
  description: string;
  amount: number;
  label?: string;
  skip?: boolean;
  bankAccountId?: string;
}