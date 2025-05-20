import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as UserActions from '../../actions/user-state.actions';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { LoadUserCmd } from '../../../commands/entitites/user/load-user.cmd';

@Injectable()
export class LoadUserEffect {
  constructor(
    private _actions$: Actions,
    private _loadUserCmd: LoadUserCmd
  ) {}

  loadUser$ = createEffect(() =>
    this._actions$.pipe(
      ofType(UserActions.loadUser),
      switchMap(action => {
        return this._loadUserCmd.execute();
      }),
      concatMap(response => {
        return [
          UserActions.loadUserSuccess({
            user: response.content,
          }),
        ];
      })
    )
  );
}
