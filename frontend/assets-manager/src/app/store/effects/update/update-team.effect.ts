import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as TeamsActions from '../../actions/entities/team.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateTeamCmd } from '../../../commands/entitites/teams/update-team.cmd';

@Injectable()
export class UpdateTeamEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _updateTeamCmd: UpdateTeamCmd
  ) {}

  updateTeam$ = createEffect(() =>
    this._actions$.pipe(
      ofType(TeamsActions.updateTeam),
      switchMap(action => {
        return this._updateTeamCmd.execute(action.team);
      }),
      concatMap(response => {
        return [
          TeamsActions.updateTeamSuccess({
            team: response.content,
          }),
        ];
      })
    )
  );
}
