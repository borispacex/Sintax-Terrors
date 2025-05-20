import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  CreateTeamRequest,
  Team,
  UpdateTeamRequest,
} from '../../../pages/team/interfaces/team.interface';

export const loadTeams = createAction(
  '[Team] Load Teams',
  props<{ params: RequestParams }>()
);

export const loadTeamsSuccess = createAction(
  '[Team] Load Teams Success',
  props<{ teams: Team[]; pagination: PaginationMetaData }>()
);

export const updateTeam = createAction(
  '[Team] Update Teams',
  props<{ team: UpdateTeamRequest }>()
);

export const updateTeamSuccess = createAction(
  '[Team] Update Teams Success',
  props<{ team: Team }>()
);

export const deleteTeam = createAction(
  '[Team] Remove Team',
  props<{ id: number }>()
);

export const deleteTeamSuccess = createAction(
  '[Team] Remove Team Success',
  props<{ id: number }>()
);

export const createTeam = createAction(
  '[Team] Create Team',
  props<{ team: CreateTeamRequest }>()
);

export const createTeamSuccess = createAction(
  '[Team] Create Team Success',
  props<{ team: Team }>()
);
