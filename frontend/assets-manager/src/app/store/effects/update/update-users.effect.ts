import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as UsersActions from '../../actions/entities/users.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateUsersCmd } from '../../../commands/entitites/users/update-users.cmd';

@Injectable()
export class UpdateUsersEffect {
  constructor(
    private _actions$: Actions,
    private _updateUsersCmd: UpdateUsersCmd
  ) {}

  updateUsers$ = createEffect(() =>
    this._actions$.pipe(
      ofType(UsersActions.updateUser),
      switchMap(action => {
        return this._updateUsersCmd.execute(action.user);
      }),
      concatMap(response => {
        return [
          UsersActions.updateUserSuccess({
            user: response.content,
          }),
        ];
      })
    )
  );
}
