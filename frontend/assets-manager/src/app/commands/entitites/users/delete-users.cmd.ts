import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import { User } from '../../../pages/user/interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class DeleteUsersCmd implements FormatCommand<CommonResponse<User>> {
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(id: number): Observable<CommonResponse<User>> {
    return this._httpClient
      .delete<CommonResponse<User>>(this._urlBuilder(id))
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'User was deleted successfully.',
          });
        }),
        catchError(error => this._errorHandler(error))
      );
  }

  private _urlBuilder(id: number): string {
    return API_PATHS.users.delete.generate({ id });
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while deleting user.',
    });
    return throwError(error);
  }
}
