import { FormatCommand } from '../../root/format.cmd';
import { Request } from '../../../pages/request/interfaces/request.interface';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';

@Injectable({
  providedIn: 'root',
})
export class LoadRequestsCmd
  implements FormatCommand<CommonPaginationResponse<Request>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(
    params: RequestParams
  ): Observable<CommonPaginationResponse<Request>> {
    return this._httpClient
      .get<CommonPaginationResponse<Request>>(this._urlBuilder(params))
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(params: RequestParams): string {
    return API_PATHS.request.getByPage.generate(params);
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading requests.',
    });
    return throwError(error);
  }
}
