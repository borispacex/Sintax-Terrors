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
  AssignmentEmployee,
  UpdateAssignmentRequest,
} from '../../../pages/assignment/interfaces/assignment-employee.interface';

@Injectable({
  providedIn: 'root',
})
export class UpdateAssignmentsCmd
  implements FormatCommand<CommonResponse<AssignmentEmployee>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _modalService: ModalService,
    private readonly _toastService: ToastService
  ) {}

  execute(
    request: UpdateAssignmentRequest
  ): Observable<CommonResponse<AssignmentEmployee>> {
    return this._httpClient
      .put<CommonResponse<AssignmentEmployee>>(this._urlBuilder(), request)
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'Assignment was updated successfully.',
          });
        }),
        catchError(error => this._errorHandler(error)),
        finalize(() => {
          this._modalService.close();
        })
      );
  }

  private _urlBuilder(): string {
    return API_PATHS.assignment.update.generate();
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while updating assignment.',
    });
    return throwError(error);
  }
}
