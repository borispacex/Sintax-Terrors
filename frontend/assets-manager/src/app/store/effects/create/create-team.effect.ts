import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as TeamsActions from '../../actions/entities/team.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as TeamSelectors from '../../selectors/entities/team-state.selectors';
import { Store } from '@ngrx/store';
import { CreateTeamCmd } from '../../../commands/entitites/teams/create-team.cmd';

@Injectable()
export class CreateTeamEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _store$: Store,
    private readonly _createTeamCmd: CreateTeamCmd
  ) {}

  createTeam$ = createEffect(() =>
    this._actions$.pipe(
      ofType(TeamsActions.createTeam),
      switchMap(action => {
        return this._createTeamCmd.execute(action.team);
      }),
      switchMap(response => {
        return this._store$.select(TeamSelectors.selectParams).pipe(first());
      }),
      concatMap(params => {
        return [
          TeamsActions.loadTeams({
            params,
          }),
        ];
      })
    )
  );
}
