import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { EMPTY, from, Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { catchError, switchMap } from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  private readonly _EXCLUDED_URLS = [
    '/auth-manager/public/login',
    '/auth-manager/public/refreshToken',
  ];
  constructor(private _authService: AuthService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    if (this._EXCLUDED_URLS.some(url => request.url.includes(url))) {
      return next.handle(request);
    }
    return from(this._authService.isTokenValid()).pipe(
      switchMap(isValid => {
        if (!isValid) {
          this._authService.logout();
          return EMPTY;
        }

        const token = this._authService.getToken();
        const cloned = request.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`,
          },
          headers: request.headers.delete('Needs-Auth'),
        });

        return next.handle(cloned);
      }),
      catchError(() => {
        return EMPTY;
      })
    );
  }
}
