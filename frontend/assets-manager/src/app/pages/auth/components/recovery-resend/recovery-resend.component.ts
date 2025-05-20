import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'am-public-recovery-resend',
  templateUrl: './recovery-resend.component.html',
  styleUrls: ['./recovery-resend.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
//TODO Implement recovery resend screen
export class RecoveryResendComponent {
  constructor(private _router: Router) {}

  redirectToLoginUser(): void {
    this._router.navigate(['/public/auth/login']);
  }
}
