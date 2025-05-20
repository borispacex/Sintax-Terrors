import { HttpClient } from '@angular/common/http';
import { HTTPErrorHandler } from './http-abstract-error-handler.service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

export abstract class HttpAdapterService {
  protected abstract getHttpClient(): HttpClient;

  protected abstract getErrorHandler(): HTTPErrorHandler;

  protected abstract getOptions(): any;

  protected abstract getBaseUrl(): string;

  private createOptions(extraOptions: any): { [key: string]: string } {
    let options = this.getOptions();
    if (extraOptions) {
      const newOptions = options;

      Object.keys(extraOptions).forEach(option => {
        if (options === 'headers') {
          newOptions[option] = { ...extraOptions[option], ...options[option] };
          return;
        }
        newOptions[option] = extraOptions[option];
      });

      options = extraOptions;
    }

    return options;
  }

  get<T = any>(url: string, options?: any): Observable<T> {
    return this.getHttpClient()
      .get<T>(this.getBaseUrl() + url, this.createOptions(options))
      .pipe(
        catchError(error => this.getErrorHandler().handleError(error))
      ) as Observable<T>;
  }

  post<T = any>(url: string, body: any, options?: any): Observable<T> {
    return this.getHttpClient()
      .post<T>(this.getBaseUrl() + url, body, this.createOptions(options))
      .pipe(
        catchError(error => this.getErrorHandler().handleError(error))
      ) as Observable<T>;
  }

  put<T = any>(url: string, body: any, options?: any): Observable<T> {
    return this.getHttpClient()
      .put<T>(this.getBaseUrl() + url, body, this.createOptions(options))
      .pipe(
        catchError(error => this.getErrorHandler().handleError(error))
      ) as Observable<T>;
  }

  patch<T = any>(url: string, body: any, options?: any): Observable<T> {
    return this.getHttpClient()
      .patch<T>(this.getBaseUrl() + url, body, this.createOptions(options))
      .pipe(
        catchError(error => this.getErrorHandler().handleError(error))
      ) as Observable<T>;
  }

  delete<T = any>(url: string, options?: any): Observable<T> {
    return this.getHttpClient()
      .delete<T>(this.getBaseUrl() + url, this.createOptions(options))
      .pipe(
        catchError(error => this.getErrorHandler().handleError(error))
      ) as Observable<T>;
  }
}
