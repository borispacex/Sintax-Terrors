import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import { User } from '../../../pages/user/interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class LoadUsersCmd
  implements FormatCommand<CommonPaginationResponse<User>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(params: RequestParams): Observable<CommonPaginationResponse<User>> {
    return this._httpClient
      .get<CommonPaginationResponse<User>>(this._urlBuilder(params))
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(params: RequestParams): string {
    return API_PATHS.users.getByPage.generate(params);
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading users.',
    });
    return throwError(error);
  }
}
