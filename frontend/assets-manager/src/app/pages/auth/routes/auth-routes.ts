import { Routes } from '@angular/router';
import { LoginUserComponent } from '../components/login-user/login-user.component';
import { PasswordChangeComponent } from '../components/password-change/password-change.component';
import { PasswordRecoveryComponent } from '../components/password-recovery/password-recovery.component';
import { RecoveryResendComponent } from '../components/recovery-resend/recovery-resend.component';
import { AuthComponent } from '../auth.component';

export const AUTH_ROUTES_CONFIG: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: '',
    component: AuthComponent,
    children: [
      {
        path: 'login',
        component: LoginUserComponent,
      },
      {
        path: 'password-recovery',
        component: PasswordRecoveryComponent,
      },
      {
        path: 'recovery-resend',
        component: RecoveryResendComponent,
      },
      {
        path: 'password-change',
        component: PasswordChangeComponent,
      },
    ],
  },
];
