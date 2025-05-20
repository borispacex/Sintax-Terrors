import { Actions, createEffect, ofType } from '@ngrx/effects';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as AssignmentActions from '../../actions/entities/assignment.action';
import * as AssignmentSelectors from '../../selectors/entities/assignment-state.selectors';
import { Store } from '@ngrx/store';
import { DeleteAssignmentsCmd } from '../../../commands/entitites/assignments/delete-assignments.cmd';

@Injectable()
export class DeleteAssignmentsEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _deleteAssignmentsCmd: DeleteAssignmentsCmd
  ) {}

  deleteAssignment$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssignmentActions.deleteAssignment),
      switchMap(action => {
        return this._deleteAssignmentsCmd.execute(action.assignment);
      }),
      switchMap(response => {
        return this._store$
          .select(AssignmentSelectors.selectPagination)
          .pipe(first());
      }),
      concatMap(pagination => {
        return [
          AssignmentActions.loadAssignments({
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
