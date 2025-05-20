import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as TeamsActions from '../../actions/entities/team.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { DeleteTeamCmd } from '../../../commands/entitites/teams/delete-team.cmd';
import * as TeamSelectors from '../../selectors/entities/team-state.selectors';
import { Store } from '@ngrx/store';

@Injectable()
export class DeleteTeamEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _store$: Store,
    private readonly _deleteTeamCmd: DeleteTeamCmd
  ) {}

  deleteTeam$ = createEffect(() =>
    this._actions$.pipe(
      ofType(TeamsActions.deleteTeam),
      switchMap(action => {
        return this._deleteTeamCmd.execute(action.id);
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
