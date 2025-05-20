import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { requestEntityAdapter } from '../../states/entities/request-state';
import { Request } from '../../../pages/request/interfaces/request.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectRequestsState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.requests
);

export const requestSelectors =
  requestEntityAdapter.getSelectors(selectRequestsState);

export const selectEntities = createSelector(
  requestSelectors.selectAll,
  requests => requests
);

export const selectRequestData = createSelector(selectRequestsState, state => ({
  entities: Object.values(state.entities) as Request[],
  pagination: state.pagination,
  isLoading: state.loading,
  params: state.params,
}));

export const selectIsLoading = createSelector(
  selectRequestsState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectRequestsState,
  state => state.pagination
);

export const selectParams = createSelector(
  selectRequestsState,
  state => state.params
);
