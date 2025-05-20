import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as RequestsActions from '../../actions/entities/request.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { DeleteRequestsCmd } from '../../../commands/entitites/requests/delete-requests.cmd';
import * as RequestSelectors from '../../selectors/entities/request-state.selectors';
import { Store } from '@ngrx/store';

@Injectable()
export class DeleteRequestsEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _deleteRequestsCmd: DeleteRequestsCmd
  ) {}

  deleteRequest$ = createEffect(() =>
    this._actions$.pipe(
      ofType(RequestsActions.deleteRequest),
      switchMap(action => {
        return this._deleteRequestsCmd.execute(action.id);
      }),
      switchMap(response => {
        return this._store$
          .select(RequestSelectors.selectPagination)
          .pipe(first());
      }),
      concatMap(pagination => {
        return [
          RequestsActions.loadRequests({
            params: {
              page: pagination.currentPage,
              pageSize: pagination.pageSize,
            },
          }),
        ];
      })
    )
  );
}
