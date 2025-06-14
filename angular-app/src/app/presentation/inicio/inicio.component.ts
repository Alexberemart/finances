import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CuentaService } from '../../infrastructure/services/cuenta.service'; // <-- FIXED
import { BankAccount } from '../../domain/models/bank-account.model';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  private cuentaService = inject(CuentaService);

  bankAccount?: BankAccount;
  ultimaActualizacion = new Date();

  ngOnInit(): void {
    this.cuentaService.getBankAccount().subscribe(account => {
      this.bankAccount = account;
      this.ultimaActualizacion = new Date();
    });
  }
}
