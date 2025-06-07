import { Routes } from '@angular/router';
import { InicioComponent } from './presentation/inicio/inicio.component';
import { MovimientosComponent } from './presentation/movimientos/movimientos.component';
import { ConfiguracionComponent } from './presentation/configuracion/configuracion.component';

export const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'movimientos', component: MovimientosComponent },
  { path: 'configuracion', component: ConfiguracionComponent }
];
