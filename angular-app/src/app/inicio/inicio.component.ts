import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CuentaService } from '../services/cuenta.service';
import { BankAccount } from '../models/bank-account.model';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  bankAccount?: BankAccount;
  ultimaActualizacion = new Date();

  constructor(private cuentaService: CuentaService) {}

  ngOnInit(): void {
    this.cuentaService.getBankAccount().subscribe(account => {
      this.bankAccount = account;
      this.ultimaActualizacion = new Date();
    });
  }
}
