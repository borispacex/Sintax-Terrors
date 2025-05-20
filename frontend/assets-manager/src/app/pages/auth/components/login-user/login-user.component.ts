import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
} from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../../services/auth.service';
import { ToastService } from '../../../../modules/shared/toast/services/toast.service';
import { catchError, finalize, tap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'am-public-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginUserComponent {
  public loading: boolean = false;
  public loginForm: FormGroup;

  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _authService: AuthService,
    private _toastService: ToastService,
    private _cdr: ChangeDetectorRef
  ) {
    this.loginForm = this._fb.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      password: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  public redirectToPasswordRecovery(): void {
    this._router.navigate(['/public/auth/password-recovery']);
  }

  public login(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;

    // TODO: Subscription - Refactor to use async pipe or an effect
    this._authService
      .login(this.loginForm.value)
      .pipe(
        tap(res => {
          this._authService.setToken(res.token);
          this._authService.setRefreshToken(res.refreshToken);
          this._toastService.show({
            message: 'Successfully logged in.',
            type: 'success',
          });
          this._router.navigate(['/secure/dashboard']);
        }),
        catchError(() => {
          this._toastService.show({
            message: 'Error connecting to the server',
            type: 'error',
          });
          return of(false);
        }),
        finalize(() => {
          this.loading = false;
          this._cdr.markForCheck();
        })
      )
      .subscribe();
  }
}
