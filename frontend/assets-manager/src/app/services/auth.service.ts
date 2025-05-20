import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { CookieService } from 'ngx-cookie-service';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { LoginRequest } from '../modules/public/interfaces/login.request.interface';
import { LoginResponse } from '../modules/public/interfaces/login.response.interface';
import { RefreshTokenRequest } from '../modules/public/interfaces/refresh-token.request.interface';
import { Router } from '@angular/router';
import { HttpClientService } from './http/http-client.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly _TOKEN = 'auth_token';
  private readonly _REFRESH_TOKEN = 'auth_refresh_token';
  private readonly _API_URL = '/auth-manager/public';

  constructor(
    private _cookieService: CookieService,
    private _httpClient: HttpClientService,
    private _router: Router
  ) {}

  public login(dto: LoginRequest): Observable<LoginResponse> {
    return this._httpClient.post<LoginResponse>(`${this._API_URL}/login`, dto);
  }

  public refreshToken(refreshToken: RefreshTokenRequest): Observable<boolean> {
    return this._httpClient
      .post<LoginResponse>(`${this._API_URL}/refreshToken`, refreshToken)
      .pipe(
        tap(res => {
          this.setToken(res.token);
        }),
        map(() => true),
        catchError(() => of(false))
      );
  }

  public logout() {
    this.removeToken();
    this._router.navigate(['/public/login']);
  }

  public setToken(token: string): void {
    this._cookieService.set(this._TOKEN, token);
  }

  public setRefreshToken(token: string): void {
    this._cookieService.set(this._REFRESH_TOKEN, token);
  }

  public getToken(): string | null {
    const token = this._cookieService.get(this._TOKEN);
    return token || null;
  }

  public getRefreshToken(): string | null {
    const token = this._cookieService.get(this._REFRESH_TOKEN);
    return token || null;
  }

  public removeToken(): void {
    this._cookieService.delete(this._TOKEN, '/');
    this._cookieService.delete(this._REFRESH_TOKEN, '/');
  }

  async isTokenValid(): Promise<boolean> {
    const token = this.getToken();
    if (!token) {
      return false;
    }

    try {
      const decoded: any = jwtDecode(token);
      const now = Date.now().valueOf() / 1000;
      const isValid = decoded.exp && decoded.exp > now;
      if (!isValid) {
        return this._validateWithRefreshToken();
      }
      return isValid;
    } catch (error) {
      return false;
    }
  }

  private async _validateWithRefreshToken(): Promise<boolean> {
    const refreshToken = this.getRefreshToken();
    if (!refreshToken) {
      return false;
    }
    return await this.refreshToken({
      refreshToken,
    } as RefreshTokenRequest).toPromise();
  }
}
