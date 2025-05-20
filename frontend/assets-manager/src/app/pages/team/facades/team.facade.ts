import { Store } from '@ngrx/store';
import * as TeamSelectors from '../../../store/selectors/entities/team-state.selectors';
import { Observable } from 'rxjs';

import * as TeamActions from '../../../store/actions/entities/team.action';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Injectable } from '@angular/core';
import {
  CreateTeamRequest,
  Team,
  TeamData,
  UpdateTeamRequest,
} from '../interfaces/team.interface';

@Injectable()
export class TeamFacade {
  constructor(private _store: Store) {}

  public getTeamData$(): Observable<TeamData> {
    return this._store.select(TeamSelectors.selectTeamData);
  }

  public getTeams$(): Observable<Team[]> {
    return this._store.select(TeamSelectors.selectEntities);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(TeamSelectors.selectPagination);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(TeamSelectors.selectIsLoading);
  }

  public loadTeams(params: RequestParams): void {
    this._store.dispatch(TeamActions.loadTeams({ params }));
  }

  public updateTeam(request: UpdateTeamRequest): void {
    this._store.dispatch(TeamActions.updateTeam({ team: request }));
  }

  public deleteTeam(id: number): void {
    this._store.dispatch(TeamActions.deleteTeam({ id }));
  }

  public createTeam(request: CreateTeamRequest): void {
    this._store.dispatch(TeamActions.createTeam({ team: request }));
  }
}
