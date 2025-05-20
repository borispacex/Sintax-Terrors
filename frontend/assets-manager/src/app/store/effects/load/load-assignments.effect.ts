import { Actions, createEffect, ofType } from '@ngrx/effects';
import { concatMap, switchMap } from 'rxjs/operators';
import * as AssignmentActions from '../../actions/entities/assignment.action';
import { Injectable } from '@angular/core';
import { LoadAssignmentsCmd } from '../../../commands/entitites/assignments/load-assignments.cmd';

@Injectable()
export class LoadAssignmentsEffect {
  constructor(
    private _actions$: Actions,
    private _loadAssignmentsCmd: LoadAssignmentsCmd
  ) {}

  loadAssignments$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssignmentActions.loadAssignments),
      switchMap(action => {
        return this._loadAssignmentsCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          AssignmentActions.loadAssignmentsSuccess({
            assignments: response.content.items,
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
