import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { teamEntityAdapter } from '../../states/entities/team-state';
import { Team } from '../../../pages/team/interfaces/team.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectTeamsState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.teams
);

export const teamSelectors = teamEntityAdapter.getSelectors(selectTeamsState);

export const selectTeamData = createSelector(selectTeamsState, state => ({
  entities: Object.values(state.entities) as Team[],
  pagination: state.pagination,
  isLoading: state.loading,
  params: state.params,
}));

export const selectEntities = createSelector(
  teamSelectors.selectAll,
  teams => teams
);

export const selectIsLoading = createSelector(
  selectTeamsState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectTeamsState,
  state => state.pagination
);

export const selectParams = createSelector(
  selectTeamsState,
  state => state.params ?? {}
);
