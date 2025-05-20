import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';
import { Transaction } from '../../../pages/history/interfaces/history.interface';

@Injectable({
  providedIn: 'root',
})
export class LoadAssetsHistoryCmd
  implements FormatCommand<CommonResponse<Transaction[]>>
{
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _toastService: ToastService
  ) {}

  execute(assetId: string): Observable<CommonResponse<Transaction[]>> {
    return this._httpClient
      .get<CommonResponse<Transaction[]>>(this._urlBuilder(assetId))
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(assetId: string): string {
    return API_PATHS.asset.getAssetHistory.generate(assetId);
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while loading assets.',
    });
    return throwError(error);
  }
}
