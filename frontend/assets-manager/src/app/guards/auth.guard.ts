import { Injectable } from '@angular/core';
import { CanActivate, CanLoad, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate, CanLoad {
  private static _loginUrl = '/public/login';
  constructor(
    private _authService: AuthService,
    private _router: Router
  ) {}

  public canActivate(): Promise<boolean> {
    return this._checkAuth();
  }

  public canLoad(): Promise<boolean> {
    return this._checkAuth();
  }

  private async _checkAuth(): Promise<boolean> {
    const isValid = await this._authService.isTokenValid();
    if (isValid) {
      return true;
    } else {
      this._router.navigateByUrl(AuthGuard._loginUrl);
      return false;
    }
  }
}
