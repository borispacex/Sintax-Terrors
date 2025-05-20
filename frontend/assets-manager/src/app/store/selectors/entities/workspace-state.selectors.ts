import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { workspaceEntityAdapter } from '../../states/entities/workspace-state';
import { Workspace } from '../../../pages/workspace/interfaces/workspace.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectWorkspacesState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.workspaces
);

export const workspaceSelectors = workspaceEntityAdapter.getSelectors(
  selectWorkspacesState
);

export const selectEntities = createSelector(
  workspaceSelectors.selectAll,
  workspaces => workspaces
);

export const selectWorkspaceData = createSelector(
  selectWorkspacesState,
  state => ({
    entities: Object.values(state.entities) as Workspace[],
    pagination: state.pagination,
    isLoading: state.loading,
    params: state.params,
  })
);

export const selectIsLoading = createSelector(
  selectWorkspacesState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectWorkspacesState,
  state => state.pagination
);

export const selectParams = createSelector(
  selectWorkspacesState,
  state => state.params
);
