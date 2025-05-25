import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { MovimientosComponent } from './movimientos/movimientos.component';
import { ConfiguracionComponent } from './configuracion/configuracion.component';

export const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'movimientos', component: MovimientosComponent },
  { path: 'configuracion', component: ConfiguracionComponent }
];
