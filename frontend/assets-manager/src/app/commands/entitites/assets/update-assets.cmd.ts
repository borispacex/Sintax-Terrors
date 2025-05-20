import { FormatCommand } from '../../root/format.cmd';
import {
  Asset,
  UpdateAssetRequest,
} from '../../../pages/asset/interfaces/asset.interface';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { catchError, finalize, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';
import { ToastService } from '../../../modules/shared/toast/services/toast.service';

@Injectable({
  providedIn: 'root',
})
export class UpdateAssetsCmd implements FormatCommand<CommonResponse<Asset>> {
  constructor(
    private readonly _httpClient: HttpClientService,
    private readonly _modalService: ModalService,
    private readonly _toastService: ToastService
  ) {}

  execute(request: UpdateAssetRequest): Observable<CommonResponse<Asset>> {
    return this._httpClient
      .put<CommonResponse<Asset>>(this._urlBuilder(), request)
      .pipe(
        tap(() => {
          this._toastService.show({
            type: 'success',
            message: 'Asset was updated successfully.',
          });
        }),
        catchError(error => this._errorHandler(error)),
        finalize(() => {
          this._modalService.close();
        })
      );
  }

  private _urlBuilder(): string {
    return API_PATHS.asset.update.generate();
  }

  private _errorHandler(error: any): Observable<never> {
    this._toastService.show({
      type: 'error',
      message: 'An error has occurred while updating asset.',
    });
    return throwError(error);
  }
}
