import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  CreateUserRequest,
  UpdateUserRequest,
  User,
} from '../../../pages/user/interfaces/user.interface';

export const loadUsers = createAction(
  '[Users] Load Users',
  props<{ params: RequestParams }>()
);

export const loadUsersSuccess = createAction(
  '[Users] Load Users Success',
  props<{ users: User[]; pagination: PaginationMetaData }>()
);

export const updateUser = createAction(
  '[Users] Update Users',
  props<{ user: UpdateUserRequest }>()
);

export const updateUserSuccess = createAction(
  '[Users] Update Users Success',
  props<{ user: User }>()
);

export const createUser = createAction(
  '[Users] Create User',
  props<{ user: CreateUserRequest }>()
);

export const createUserSuccess = createAction(
  '[Users] Create User Success',
  props<{ user: User }>()
);
