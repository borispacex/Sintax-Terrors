import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  CreateWorkspaceRequest,
  UpdateWorkspaceRequest,
  Workspace,
} from '../../../pages/workspace/interfaces/workspace.interface';

export const loadWorkspaces = createAction(
  '[Workspace] Load Workspaces',
  props<{ params: RequestParams }>()
);

export const loadWorkspacesSuccess = createAction(
  '[Workspace] Load Workspaces Success',
  props<{ workspaces: Workspace[]; pagination: PaginationMetaData }>()
);

export const updateWorkspace = createAction(
  '[Workspace] Update Workspaces',
  props<{ workspace: UpdateWorkspaceRequest }>()
);

export const updateWorkspaceSuccess = createAction(
  '[Workspace] Update Workspaces Success',
  props<{ workspace: Workspace }>()
);

export const deleteWorkspace = createAction(
  '[Workspace] Remove Workspace',
  props<{ id: number }>()
);

export const deleteWorkspaceSuccess = createAction(
  '[Workspace] Remove Workspace Success',
  props<{ id: number }>()
);

export const createWorkspace = createAction(
  '[Workspace] Create Workspace',
  props<{ workspace: CreateWorkspaceRequest }>()
);

export const createWorkspaceSuccess = createAction(
  '[Workspace] Create Workspace Success',
  props<{ workspace: Workspace }>()
);
