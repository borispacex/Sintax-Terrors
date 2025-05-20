import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as RequestsActions from '../../actions/entities/request.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { LoadRequestsCmd } from '../../../commands/entitites/requests/load-requests.cmd';
import { Injectable } from '@angular/core';

@Injectable()
export class LoadRequestsEffect {
  constructor(
    private _actions$: Actions,
    private _loadRequestsCmd: LoadRequestsCmd
  ) {}

  loadRequests$ = createEffect(() =>
    this._actions$.pipe(
      ofType(RequestsActions.loadRequests),
      switchMap(action => {
        return this._loadRequestsCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          RequestsActions.loadRequestsSuccess({
            requests: response.content.items,
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
