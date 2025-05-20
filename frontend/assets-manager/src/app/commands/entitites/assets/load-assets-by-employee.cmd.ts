import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import { Asset } from '../../../pages/asset/interfaces/asset.interface';

@Injectable({
  providedIn: 'root',
})
export class LoadCurrentAssetsByEmployeeCmd
  implements FormatCommand<CommonResponse<Asset[]>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(employeeId: string): Observable<CommonResponse<Asset[]>> {
    return this._httpClient
      .get<CommonResponse<Asset[]>>(this._urlBuilder(employeeId))
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(employeeId: string): string {
    return API_PATHS.asset.getCurrentByEmployee.generate(employeeId);
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading assets.',
    });
    return throwError(error);
  }
}
