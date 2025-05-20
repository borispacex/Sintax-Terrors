import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Team } from '../../../pages/team/interfaces/team.interface';

export interface TeamState extends EntityState<Team> {
  pagination: PaginationMetaData;
  params: RequestParams;
  loading: boolean;
}

export const teamEntityAdapter = createEntityAdapter<Team>({
  selectId: (entity: Team) => entity.id,
  sortComparer: false,
});

export const initialTeamState: TeamState = teamEntityAdapter.getInitialState({
  ids: [],
  entities: [],
  pagination: {
    totalPages: 0,
    pageSize: 0,
    totalItems: 0,
    currentPage: 0,
  },
  loading: false,
  params: {},
});
