import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as RequestsActions from '../../actions/entities/request.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateRequestsCmd } from '../../../commands/entitites/requests/update-requests.cmd';

@Injectable()
export class UpdateRequestsEffect {
  constructor(
    private _actions$: Actions,
    private _updateRequestCmd: UpdateRequestsCmd
  ) {}

  updateRequest$ = createEffect(() =>
    this._actions$.pipe(
      ofType(RequestsActions.updateRequest),
      switchMap(action => {
        return this._updateRequestCmd.execute(action.request);
      }),
      concatMap(response => {
        return [
          RequestsActions.updateRequestSuccess({
            request: response.content,
          }),
        ];
      })
    )
  );
}
