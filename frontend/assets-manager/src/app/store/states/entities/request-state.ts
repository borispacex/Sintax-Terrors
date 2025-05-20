import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Request } from '../../../pages/request/interfaces/request.interface';
export interface RequestState extends EntityState<Request> {
  pagination: PaginationMetaData;
  loading: boolean;
  params: RequestParams;
}

export const requestEntityAdapter = createEntityAdapter<Request>({
  selectId: (entity: Request) => entity.id,
  sortComparer: false,
});

export const initialRequestState: RequestState =
  requestEntityAdapter.getInitialState({
    ids: [],
    entities: [],
    pagination: {
      totalPages: 0,
      pageSize: 0,
      totalItems: 0,
      currentPage: 0,
    },
    loading: false,
    params: {},
  });
