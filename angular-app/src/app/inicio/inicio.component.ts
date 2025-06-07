import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { CuentaService } from '../services/cuenta.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  saldo: number = 0;
  ultimaActualizacion = new Date();

  constructor(private cuentaService: CuentaService) {}

  ngOnInit(): void {
    this.cuentaService.getSaldo().subscribe(saldo => {
      this.saldo = saldo;
      this.ultimaActualizacion = new Date();
    });
  }
}
