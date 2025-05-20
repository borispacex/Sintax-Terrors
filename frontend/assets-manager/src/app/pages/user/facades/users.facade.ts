import { Store } from '@ngrx/store';
import * as UsersSelectors from '../../../store/selectors/entities/user-state.selectors';
import { Observable } from 'rxjs';
import * as UsersActions from '../../../store/actions/entities/users.action';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Injectable } from '@angular/core';
import {
  CreateUserRequest,
  Role,
  UpdateUserRequest,
  User,
  UserData,
} from '../interfaces/user.interface';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { LoadRolesCmd } from '../../../commands/entitites/users/load-roles.cmd';

@Injectable()
export class UsersFacade {
  constructor(
    private _store: Store,
    private _loadRoleCmd: LoadRolesCmd
  ) {}

  public getUsers$(): Observable<User[]> {
    return this._store.select(UsersSelectors.selectEntities);
  }

  public getUsersData$(): Observable<UserData> {
    return this._store.select(UsersSelectors.selectUserData);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(UsersSelectors.selectPagination);
  }

  public getParams$(): Observable<RequestParams> {
    return this._store.select(UsersSelectors.selectParams);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(UsersSelectors.selectIsLoading);
  }

  public loadUsers(params: RequestParams): void {
    this._store.dispatch(UsersActions.loadUsers({ params }));
  }

  public updateUser(request: UpdateUserRequest): void {
    this._store.dispatch(UsersActions.updateUser({ user: request }));
  }

  public createUser(request: CreateUserRequest): void {
    this._store.dispatch(UsersActions.createUser({ user: request }));
  }

  public loadRoles(
    params: RequestParams
  ): Observable<CommonPaginationResponse<Role>> {
    return this._loadRoleCmd.execute(params);
  }
}
