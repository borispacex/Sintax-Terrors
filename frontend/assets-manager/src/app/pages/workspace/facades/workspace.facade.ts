import { Store } from '@ngrx/store';
import * as WorkspaceSelectors from '../../../store/selectors/entities/workspace-state.selectors';
import { Observable } from 'rxjs';
import {
  CreateWorkspaceRequest,
  UpdateWorkspaceRequest,
  Workspace,
  WorkspaceData,
} from '../interfaces/workspace.interface';
import * as WorkspaceActions from '../../../store/actions/entities/workspace.action';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Injectable } from '@angular/core';

@Injectable()
export class WorkspaceFacade {
  constructor(private _store: Store) {}

  public getWorkspaces$(): Observable<Workspace[]> {
    return this._store.select(WorkspaceSelectors.selectEntities);
  }

  public getWorkspaceData$(): Observable<WorkspaceData> {
    return this._store.select(WorkspaceSelectors.selectWorkspaceData);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(WorkspaceSelectors.selectPagination);
  }

  public getParams$(): Observable<RequestParams> {
    return this._store.select(WorkspaceSelectors.selectParams);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(WorkspaceSelectors.selectIsLoading);
  }

  public loadWorkspaces(params: RequestParams): void {
    this._store.dispatch(WorkspaceActions.loadWorkspaces({ params }));
  }

  public updateWorkspace(request: UpdateWorkspaceRequest): void {
    this._store.dispatch(
      WorkspaceActions.updateWorkspace({ workspace: request })
    );
  }

  public deleteWorkspace(id: number): void {
    this._store.dispatch(WorkspaceActions.deleteWorkspace({ id }));
  }

  public createWorkspace(request: CreateWorkspaceRequest): void {
    this._store.dispatch(
      WorkspaceActions.createWorkspace({ workspace: request })
    );
  }
}
