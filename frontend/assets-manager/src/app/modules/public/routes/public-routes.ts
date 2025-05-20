import { Routes } from '@angular/router';
import { PublicComponent } from '../public.component';

export const PUBLIC_ROUTES_CONFIG: Routes = [
  {
    path: '',
    component: PublicComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'auth' },
      {
        path: 'auth',
        loadChildren: () =>
          import('../../../pages/auth/auth.module').then(m => m.AuthModule),
      },
    ],
  },
];
