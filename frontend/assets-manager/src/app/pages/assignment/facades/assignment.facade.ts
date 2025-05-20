import { Store } from '@ngrx/store';
import * as AssignmentSelectors from '../../../store/selectors/entities/assignment-state.selectors';
import { Observable } from 'rxjs';
import * as AssignmentActions from '../../../store/actions/entities/assignment.action';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Injectable } from '@angular/core';
import {
  AssignmentData,
  AssignmentEmployee,
  CreateAssignmentRequest,
  UpdateAssignmentRequest,
} from '../interfaces/assignment-employee.interface';

import { CommonResponse } from '../../../interfaces/common-response/common.response.interface';
import { LoadCurrentAssetsByEmployeeCmd } from '../../../commands/entitites/assets/load-assets-by-employee.cmd';
import { LoadEmployeesCmd } from '../../../commands/entitites/employees/load-employees.cmd';
import { LoadAssetsCmd } from '../../../commands/entitites/assets/load-assets.cmd';
import { Asset } from '../../asset/interfaces/asset.interface';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { Employee } from '../../employee/interfaces/employee.interface';
import { LoadAssetsHistoryCmd } from '../../../commands/entitites/assets/load-assets-history.cmd';
import { Transaction } from '../../history/interfaces/history.interface';

@Injectable()
export class AssignmentFacade {
  constructor(
    private _store: Store,
    private _loadCurrentAssetsByEmployeeCmd: LoadCurrentAssetsByEmployeeCmd,
    private _loadAssetsHistoryCmd: LoadAssetsHistoryCmd,
    private _loadEmployeesCmd: LoadEmployeesCmd,
    private _loadAssetCmd: LoadAssetsCmd
  ) {}

  public getAssignmentsData$(): Observable<AssignmentData> {
    return this._store.select(AssignmentSelectors.selectAssignmentData);
  }

  public getAssignments$(): Observable<AssignmentEmployee[]> {
    return this._store.select(AssignmentSelectors.selectEntities);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(AssignmentSelectors.selectPagination);
  }

  public getParams$(): Observable<RequestParams> {
    return this._store.select(AssignmentSelectors.selectParams);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(AssignmentSelectors.selectIsLoading);
  }

  public loadAssignments(params: RequestParams): void {
    this._store.dispatch(AssignmentActions.loadAssignments({ params }));
  }

  public updateAssignment(request: UpdateAssignmentRequest): void {
    this._store.dispatch(
      AssignmentActions.updateAssignment({ assignment: request })
    );
  }

  public updateParams(params: RequestParams): void {
    this._store.dispatch(AssignmentActions.updateParams({ params }));
  }

  public createAssignment(request: CreateAssignmentRequest): void {
    this._store.dispatch(
      AssignmentActions.createAssignment({ assignment: request })
    );
  }

  public removeAssignment(request: CreateAssignmentRequest): void {
    this._store.dispatch(
      AssignmentActions.deleteAssignment({ assignment: request })
    );
  }

  public loadCurrentAssetsByEmployee(
    employeeId: string
  ): Observable<CommonResponse<Asset[]>> {
    return this._loadCurrentAssetsByEmployeeCmd.execute(employeeId);
  }

  public loadAssetsHistory(
    assetId: string
  ): Observable<CommonResponse<Transaction[]>> {
    return this._loadAssetsHistoryCmd.execute(assetId);
  }

  public loadAssets(
    params: RequestParams
  ): Observable<CommonPaginationResponse<Asset>> {
    return this._loadAssetCmd.execute(params);
  }

  public loadEmployees(
    params: RequestParams
  ): Observable<CommonPaginationResponse<Employee>> {
    return this._loadEmployeesCmd.execute(params);
  }
}
