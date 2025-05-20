import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'am-public-password-recovery',
  templateUrl: './password-recovery.component.html',
  styleUrls: ['./password-recovery.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
//TODO Implement password recovery screen
export class PasswordRecoveryComponent {
  constructor(private _router: Router) {}

  redirectToLoginUser(): void {
    this._router.navigate(['/public/auth/login']);
  }

  redirectToRecoveryResend(): void {
    this._router.navigate(['/public/auth/recovery-resend']);
  }
}
