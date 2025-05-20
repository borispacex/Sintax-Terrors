import { HttpAdapterService } from './http-adapter.service';
import { HttpClient } from '@angular/common/http';
import { HTTPErrorHandler } from './http-abstract-error-handler.service';
import { HttpClientErrorHandlerService } from './http-client-error-handler.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class HttpClientService extends HttpAdapterService {
  constructor(
    private httpClient: HttpClient,
    private errorHandler: HttpClientErrorHandlerService
  ) {
    super();
  }
  protected getBaseUrl(): string {
    return '';
  }

  protected getErrorHandler(): HTTPErrorHandler {
    return this.errorHandler;
  }

  protected getHttpClient(): HttpClient {
    return this.httpClient;
  }

  protected getOptions(): any {
    return {};
  }
}
