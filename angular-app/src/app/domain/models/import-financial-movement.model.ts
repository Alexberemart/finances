export interface ImportFinancialMovement {
  date: Date;
  description: string;
  amount: number;
  label?: string;
  skip?: boolean;
  bankAccountId?: string;
  type?: 'gastos' | 'ingresos'; // Add this line to persist the selected type
}