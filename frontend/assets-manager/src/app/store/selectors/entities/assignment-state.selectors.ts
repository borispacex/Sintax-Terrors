import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { assignmentEntityAdapter } from '../../states/entities/asignment-state';
import { AssignmentEmployee } from '../../../pages/assignment/interfaces/assignment-employee.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectAssignmentsState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.assignments
);

export const assignmentSelectors = assignmentEntityAdapter.getSelectors(
  selectAssignmentsState
);

export const selectEntities = createSelector(
  assignmentSelectors.selectAll,
  workspaces => workspaces
);

export const selectAssignmentData = createSelector(
  selectAssignmentsState,
  state => ({
    entities: Object.values(state.entities) as AssignmentEmployee[],
    pagination: state.pagination,
    isLoading: state.loading,
    params: state.params,
  })
);

export const selectIsLoading = createSelector(
  selectAssignmentsState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectAssignmentsState,
  state => state.pagination
);

export const selectParams = createSelector(
  selectAssignmentsState,
  state => state.params
);
