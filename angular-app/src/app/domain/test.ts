// src/app/domain/test-boundary.ts
import { inject, Injectable } from '@angular/core';
import { CuentaService } from '../infrastructure/services/cuenta.service';

@Injectable({ providedIn: 'root' })
export class ImportRestService {
private something = inject(CuentaService);
}