import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError, finalize, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import {
  Notification,
  UpdateNotificationRequest,
} from '../../../modules/shared/notification/interface/notification.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';

@Injectable({
  providedIn: 'root',
})
export class UpdateNotificationsCmd
  implements FormatCommand<CommonResponse<Notification>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _modalService: ModalService,
    private readonly _toastService: ToastService
  ) {}

  execute(
    request: UpdateNotificationRequest
  ): Observable<CommonResponse<Notification>> {
    return this._httpClient
      .put<CommonResponse<Notification>>(this._urlBuilder(), request)
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'Notification was updated successfully.',
          });
        }),
        catchError(error => this._errorHandler(error)),
        finalize(() => {
          this._modalService.close();
        })
      );
  }

  private _urlBuilder(): string {
    return API_PATHS.notifications.update.generate();
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while updating notification.',
    });
    return throwError(error);
  }
}
