import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as UsersActions from '../../actions/entities/users.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { LoadUsersCmd } from '../../../commands/entitites/users/load-users.cmd';

@Injectable()
export class LoadUsersEffect {
  constructor(
    private _actions$: Actions,
    private _loadUsersCmd: LoadUsersCmd
  ) {}

  loadUsers$ = createEffect(() =>
    this._actions$.pipe(
      ofType(UsersActions.loadUsers),
      switchMap(action => {
        return this._loadUsersCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          UsersActions.loadUsersSuccess({
            users: response.content.items,
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
