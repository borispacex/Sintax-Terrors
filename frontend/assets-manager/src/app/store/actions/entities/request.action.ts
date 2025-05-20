import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  CreateRequestRequest,
  Request,
  UpdateRequestRequest,
} from '../../../pages/request/interfaces/request.interface';

export const loadRequests = createAction(
  '[Request] Load Requests',
  props<{ params: RequestParams }>()
);

export const loadRequestsSuccess = createAction(
  '[Request] Load Requests Success',
  props<{ requests: Request[]; pagination: PaginationMetaData }>()
);

export const updateRequest = createAction(
  '[Request] Update Requests',
  props<{ request: UpdateRequestRequest }>()
);

export const updateRequestSuccess = createAction(
  '[Request] Update Requests Success',
  props<{ request: Request }>()
);

export const deleteRequest = createAction(
  '[Request] Remove Request',
  props<{ id: number }>()
);

export const deleteRequestSuccess = createAction(
  '[Request] Remove Request Success',
  props<{ id: number }>()
);

export const createRequest = createAction(
  '[Request] Create Request',
  props<{ request: CreateRequestRequest }>()
);

export const createRequestSuccess = createAction(
  '[Request] Create Request Success',
  props<{ request: Request }>()
);
