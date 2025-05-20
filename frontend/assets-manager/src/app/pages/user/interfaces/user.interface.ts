import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';

export interface User {
  id: string;
  username: string;
  state: UserState;
  roleName: string;
  roleId: number;
}

export interface UserData {
  entities: User[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface Role {
  id: string;
  name: string;
}

export interface CreateUserRequest {
  username: string;
  password: string;
  roleId: string;
  state: string;
}

export interface UpdateUserRequest {
  id: string;
  username: string;
  password: string;
  roleId: string;
  state: string;
}

export enum UserState {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
}
