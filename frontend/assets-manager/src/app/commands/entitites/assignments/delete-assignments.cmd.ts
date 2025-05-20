import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError, finalize, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import {
  AssignmentEmployee,
  CreateAssignmentRequest,
} from '../../../pages/assignment/interfaces/assignment-employee.interface';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';

@Injectable({
  providedIn: 'root',
})
export class DeleteAssignmentsCmd
  implements FormatCommand<CommonResponse<AssignmentEmployee>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService,
    private readonly _modalService: ModalService
  ) {}

  execute(
    request: CreateAssignmentRequest
  ): Observable<CommonResponse<AssignmentEmployee>> {
    return this._httpClient
      .post<CommonResponse<AssignmentEmployee>>(this._urlBuilder(), request)
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'Assignment was deleted successfully.',
          });
        }),
        catchError(error => this._errorHandler(error)),
        finalize(() => this._modalService.close())
      );
  }

  private _urlBuilder(): string {
    return API_PATHS.assignment.delete.generate();
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while removing assignment.',
    });
    return throwError(error);
  }
}
