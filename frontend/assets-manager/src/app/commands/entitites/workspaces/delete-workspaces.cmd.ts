import { FormatCommand } from '../../root/format.cmd';
import { Workspace } from '../../../pages/workspace/interfaces/workspace.interface';
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
export class DeleteWorkspacesCmd
  implements FormatCommand<CommonResponse<Workspace>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(id: number): Observable<CommonResponse<Workspace>> {
    return this._httpClient
      .delete<CommonResponse<Workspace>>(this._urlBuilder(id))
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'Workspace was deleted successfully.',
          });
        }),
        catchError(error => this._errorHandler(error))
      );
  }

  private _urlBuilder(id: number): string {
    return API_PATHS.workspace.delete.generate({ id });
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while deleting workspace.',
    });
    return throwError(error);
  }
}
