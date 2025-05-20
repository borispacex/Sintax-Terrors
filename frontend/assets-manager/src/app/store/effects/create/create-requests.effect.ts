import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as RequestsActions from '../../actions/entities/request.action';
import { concatMap, first, map, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as RequestSelectors from '../../selectors/entities/request-state.selectors';
import * as UserSelectors from '../../selectors/user-state.selectors';
import { Store } from '@ngrx/store';
import { CreateRequestsCmd } from '../../../commands/entitites/requests/create-requests.cmd';
import { CreateRequestRequest } from '../../../pages/request/interfaces/request.interface';

@Injectable()
export class CreateRequestsEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _createRequestsCmd: CreateRequestsCmd
  ) {}

  createRequest$ = createEffect(() =>
    this._actions$.pipe(
      ofType(RequestsActions.createRequest),
      switchMap(action => {
        return this._store$.select(UserSelectors.selectUser).pipe(
          first(),
          map(user => ({
            ...action,
            request: {
              ...action.request,
              employeeId: user?.employeeId,
            } as CreateRequestRequest,
          }))
        );
      }),
      concatMap(action => {
        return this._createRequestsCmd.execute(action.request);
      }),
      concatMap(response => {
        return this._store$.select(RequestSelectors.selectParams).pipe(first());
      }),
      concatMap(params => {
        return [
          RequestsActions.loadRequests({
            params,
          }),
        ];
      })
    )
  );
}
