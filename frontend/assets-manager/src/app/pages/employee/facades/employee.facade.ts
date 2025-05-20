import { Store } from '@ngrx/store';
import * as EmployeeSelectors from '../../../store/selectors/entities/employee-state.selectors';
import { Observable } from 'rxjs';

import * as EmployeeActions from '../../../store/actions/entities/employee.action';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Injectable } from '@angular/core';
import {
  CreateEmployeeRequest,
  Employee,
  EmployeeData,
  UpdateEmployeeRequest,
} from '../interfaces/employee.interface';
import { LoadUsersCmd } from '../../../commands/entitites/users/load-users.cmd';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { User } from '../../user/interfaces/user.interface';

@Injectable()
export class EmployeeFacade {
  constructor(
    private _store: Store,
    private _loadUsersCmd: LoadUsersCmd
  ) {}

  public getEmployeesData$(): Observable<EmployeeData> {
    return this._store.select(EmployeeSelectors.selectEmployeeData);
  }

  public getEmployees$(): Observable<Employee[]> {
    return this._store.select(EmployeeSelectors.selectEntities);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(EmployeeSelectors.selectPagination);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(EmployeeSelectors.selectIsLoading);
  }

  public loadEmployees(params: RequestParams): void {
    this._store.dispatch(EmployeeActions.loadEmployees({ params }));
  }

  public updateEmployee(request: UpdateEmployeeRequest): void {
    this._store.dispatch(EmployeeActions.updateEmployee({ employee: request }));
  }

  public deleteEmployee(id: number): void {
    this._store.dispatch(EmployeeActions.deleteEmployee({ id }));
  }

  public createEmployee(request: CreateEmployeeRequest): void {
    this._store.dispatch(EmployeeActions.createEmployee({ employee: request }));
  }

  public loadUsers(
    params: RequestParams
  ): Observable<CommonPaginationResponse<User>> {
    return this._loadUsersCmd.execute(params);
  }
}
