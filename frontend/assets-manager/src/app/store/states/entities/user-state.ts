import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { User } from '../../../pages/user/interfaces/user.interface';
export interface UsersState extends EntityState<User> {
  pagination: PaginationMetaData;
  params: RequestParams;
  loading: boolean;
}

export const userEntityAdapter = createEntityAdapter<User>({
  selectId: (entity: User) => entity.id,
  sortComparer: false,
});

export const initialUsersState: UsersState = userEntityAdapter.getInitialState({
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
