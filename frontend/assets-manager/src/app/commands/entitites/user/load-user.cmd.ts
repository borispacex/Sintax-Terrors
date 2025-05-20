import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { UserInterface } from '../../../modules/shared/profile/interfaces/user.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';

@Injectable({
  providedIn: 'root',
})
export class LoadUserCmd
  implements FormatCommand<CommonResponse<UserInterface>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(): Observable<CommonResponse<UserInterface>> {
    return this._httpClient
      .get<CommonResponse<UserInterface>>(this._urlBuilder())
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(): string {
    return API_PATHS.user.getByToken.generate();
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading user.',
    });
    return throwError(error);
  }
}
