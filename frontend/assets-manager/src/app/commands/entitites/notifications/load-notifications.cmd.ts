import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Notification } from '../../../modules/shared/notification/interface/notification.interface';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';

@Injectable({
  providedIn: 'root',
})
export class LoadNotificationsCmd
  implements FormatCommand<CommonPaginationResponse<Notification>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(
    params: RequestParams
  ): Observable<CommonPaginationResponse<Notification>> {
    return this._httpClient
      .get<CommonPaginationResponse<Notification>>(this._urlBuilder(params))
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(params: RequestParams): string {
    return API_PATHS.notifications.getByPage.generate(params);
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading notifications.',
    });
    return throwError(error);
  }
}
