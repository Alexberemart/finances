import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { GetBankAccountSummariesUseCase } from '../../application/use-cases/get-bank-account-summaries.usecase';
import { BankAccountSummary } from '../../domain/models/bank-account-summary.model';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  private getBankAccountSummariesUseCase = inject(GetBankAccountSummariesUseCase);

  bankAccounts: BankAccountSummary[] = [];
  ultimaActualizacion = new Date();

  ngOnInit(): void {
    this.getBankAccountSummariesUseCase.execute().subscribe(accounts => {
      this.bankAccounts = accounts;
      this.ultimaActualizacion = new Date();
    });
  }
}
