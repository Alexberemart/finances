import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { BankAccountService } from '../../infrastructure/services/bank-account.service'; // <-- Use BankAccountService
import { BankAccount } from '../../domain/models/bank-account.model';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  private bankAccountService = inject(BankAccountService);

  bankAccounts: BankAccount[] = [];
  ultimaActualizacion = new Date();

  ngOnInit(): void {
    this.bankAccountService.getBankAccounts().subscribe(accounts => {
      this.bankAccounts = accounts;
      this.ultimaActualizacion = new Date();
    });
  }
}
