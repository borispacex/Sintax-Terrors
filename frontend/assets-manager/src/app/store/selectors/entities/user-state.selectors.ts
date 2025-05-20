import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { userEntityAdapter } from '../../states/entities/user-state';
import { User } from '../../../pages/user/interfaces/user.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectUsersState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.users
);

export const userSelectors = userEntityAdapter.getSelectors(selectUsersState);

export const selectEntities = createSelector(
  userSelectors.selectAll,
  users => users
);

export const selectUserData = createSelector(selectUsersState, state => ({
  entities: Object.values(state.entities) as User[],
  pagination: state.pagination,
  isLoading: state.loading,
  params: state.params,
}));

export const selectIsLoading = createSelector(
  selectUsersState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectUsersState,
  state => state.pagination
);

export const selectParams = createSelector(
  selectUsersState,
  state => state.params
);
