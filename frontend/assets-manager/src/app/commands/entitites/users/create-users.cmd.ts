import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError, finalize, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import {
  CreateUserRequest,
  User,
} from '../../../pages/user/interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class CreateUsersCmd implements FormatCommand<CommonResponse<User>> {
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _modalService: ModalService,
    private readonly _toastService: ToastService
  ) {}

  execute(request: CreateUserRequest): Observable<CommonResponse<User>> {
    return this._httpClient
      .post<CommonResponse<User>>(this._urlBuilder(), request)
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'User was created successfully.',
          });
        }),
        catchError(error => this._errorHandler(error)),
        finalize(() => {
          this._modalService.close();
        })
      );
  }

  private _urlBuilder(): string {
    return API_PATHS.users.create.generate();
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while creating workspace.',
    });
    return throwError(error);
  }
}
