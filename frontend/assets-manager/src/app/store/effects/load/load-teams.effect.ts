import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as TeamsActions from '../../actions/entities/team.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { LoadTeamsCmd } from '../../../commands/entitites/teams/load-teams.cmd';
import { Injectable } from '@angular/core';

@Injectable()
export class LoadTeamsEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _loadTeamsCmd: LoadTeamsCmd
  ) {}

  loadTeams$ = createEffect(() =>
    this._actions$.pipe(
      ofType(TeamsActions.loadTeams),
      switchMap(action => {
        return this._loadTeamsCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          TeamsActions.loadTeamsSuccess({
            teams: response.content.items,
            pagination: {
              totalItems: response.content.totalItems,
              currentPage: response.content.currentPage,
              pageSize: response.content.pageSize,
              totalPages: response.content.totalPages,
            },
          }),
        ];
      })
    )
  );
}
