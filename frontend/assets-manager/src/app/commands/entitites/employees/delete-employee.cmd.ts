import { FormatCommand } from '../../root/format.cmd';
import { Employee } from '../../../pages/employee/interfaces/employee.interface';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';

@Injectable({
  providedIn: 'root',
})
export class DeleteEmployeeCmd
  implements FormatCommand<CommonResponse<Employee>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(id: number): Observable<CommonResponse<Employee>> {
    return this._httpClient
      .delete<CommonResponse<Employee>>(this._urlBuilder(id))
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'Employee was deleted successfully.',
          });
        }),
        catchError(error => this._errorHandler(error))
      );
  }

  private _urlBuilder(id: number): string {
    return API_PATHS.employee.delete.generate({ id });
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while removing employee.',
    });
    return throwError(error);
  }
}
