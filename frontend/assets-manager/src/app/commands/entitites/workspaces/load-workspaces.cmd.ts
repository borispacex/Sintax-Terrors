import { FormatCommand } from '../../root/format.cmd';
import { Workspace } from '../../../pages/workspace/interfaces/workspace.interface';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';

@Injectable({
  providedIn: 'root',
})
export class LoadWorkspacesCmd
  implements FormatCommand<CommonPaginationResponse<Workspace>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(
    params: RequestParams
  ): Observable<CommonPaginationResponse<Workspace>> {
    return this._httpClient
      .get<CommonPaginationResponse<Workspace>>(this._urlBuilder(params))
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(params: RequestParams): string {
    return API_PATHS.workspace.getByPage.generate(params);
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading workspaces.',
    });
    return throwError(error);
  }
}
