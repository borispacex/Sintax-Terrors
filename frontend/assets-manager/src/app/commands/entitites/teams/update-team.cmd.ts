import { FormatCommand } from '../../root/format.cmd';
import {
  Team,
  UpdateTeamRequest,
} from '../../../pages/team/interfaces/team.interface';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError, finalize, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';

@Injectable({
  providedIn: 'root',
})
export class UpdateTeamCmd implements FormatCommand<CommonResponse<Team>> {
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _modalService: ModalService,
    private readonly _toastService: ToastService
  ) {}

  execute(request: UpdateTeamRequest): Observable<CommonResponse<Team>> {
    return this._httpClient
      .put<CommonResponse<Team>>(this._urlBuilder(), request)
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'Team was updated successfully.',
          });
        }),
        catchError(error => this._errorHandler(error)),
        finalize(() => {
          this._modalService.close();
        })
      );
  }

  private _urlBuilder(): string {
    return API_PATHS.team.update.generate();
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while updating team.',
    });
    return throwError(error);
  }
}
