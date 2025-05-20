import { FormatCommand } from '../../root/format.cmd';
import { HttpClientService } from '../../../services/http/http-client.service';
import { Observable, throwError } from 'rxjs';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { API_PATHS } from '../../../helpers/api-paths/api-paths.helper';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Category } from '../../../pages/asset/interfaces/category.interface';

@Injectable({
  providedIn: 'root',
})
export class LoadCategoriesCmd
  implements FormatCommand<CommonPaginationResponse<Category>>
{
  constructor(private readonly httpClient: HttpClientService) {}

  execute(
    params: RequestParams
  ): Observable<CommonPaginationResponse<Category>> {
    return this.httpClient
      .get<CommonPaginationResponse<Category>>(this._urlBuilder(params))
      .pipe(catchError(error => this._errorHandler(error)));
  }

  private _urlBuilder(params: RequestParams): string {
    return API_PATHS.category.getByPage.generate(params);
  }

  private _errorHandler(error: any): Observable<never> {
    return throwError(error);
  }
}
