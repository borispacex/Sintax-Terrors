import { createFeatureSelector, createSelector } from '@ngrx/store';
import { UserState } from '../states/user.state';

export const selectUserState = createFeatureSelector<UserState>('userState');

export const selectUser = createSelector(selectUserState, state => state.user);

export const selectIsLoading = createSelector(
  selectUserState,
  state => state.loading
);
