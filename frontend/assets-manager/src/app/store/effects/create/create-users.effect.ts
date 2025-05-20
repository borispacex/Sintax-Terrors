import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as UsersActions from '../../actions/entities/users.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as UsersSelectors from '../../selectors/entities/user-state.selectors';
import { Store } from '@ngrx/store';
import { CreateUsersCmd } from '../../../commands/entitites/users/create-users.cmd';

@Injectable()
export class CreateUsersEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _createUsersCmd: CreateUsersCmd
  ) {}

  createWorkspace$ = createEffect(() =>
    this._actions$.pipe(
      ofType(UsersActions.createUser),
      switchMap(action => {
        return this._createUsersCmd.execute(action.user);
      }),
      switchMap(response => {
        return this._store$.select(UsersSelectors.selectParams).pipe(first());
      }),
      concatMap(params => {
        return [
          UsersActions.loadUsers({
            params,
          }),
        ];
      })
    )
  );
}
