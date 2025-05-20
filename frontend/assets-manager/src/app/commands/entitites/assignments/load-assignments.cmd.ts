import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { AssignmentEmployee } from '../../../pages/assignment/interfaces/assignment-employee.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';

@Injectable({
  providedIn: 'root',
})
export class LoadAssignmentsCmd
  implements FormatCommand<CommonPaginationResponse<AssignmentEmployee>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(
    params: RequestParams
  ): Observable<CommonPaginationResponse<AssignmentEmployee>> {
    return this._httpClient
      .get<
        CommonPaginationResponse<AssignmentEmployee>
      >(this._urlBuilder(params))
      .pipe(catchError(error => this.errorHandler(error)));
  }

  private _urlBuilder(params: RequestParams): string {
    return API_PATHS.assignment.getByPage.generate(params);
  }

  private errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading assignments.',
    });
    return throwError(error);
  }
}
