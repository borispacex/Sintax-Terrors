import { Store } from '@ngrx/store';
import * as RequestSelectors from '../../../store/selectors/entities/request-state.selectors';
import { Observable } from 'rxjs';

import * as RequestActions from '../../../store/actions/entities/request.action';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Injectable } from '@angular/core';
import {
  CreateRequestRequest,
  Request,
  RequestData,
  UpdateRequestRequest,
} from '../interfaces/request.interface';

@Injectable()
export class RequestFacade {
  constructor(private _store: Store) {}

  public getRequestsData$(): Observable<RequestData> {
    return this._store.select(RequestSelectors.selectRequestData);
  }

  public getRequests$(): Observable<Request[]> {
    return this._store.select(RequestSelectors.selectEntities);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(RequestSelectors.selectPagination);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(RequestSelectors.selectIsLoading);
  }

  public loadRequests(params: RequestParams): void {
    this._store.dispatch(RequestActions.loadRequests({ params }));
  }

  public updateRequest(request: UpdateRequestRequest): void {
    this._store.dispatch(RequestActions.updateRequest({ request: request }));
  }

  public deleteRequest(id: number): void {
    this._store.dispatch(RequestActions.deleteRequest({ id }));
  }

  public createRequest(request: CreateRequestRequest): void {
    this._store.dispatch(RequestActions.createRequest({ request: request }));
  }
}
