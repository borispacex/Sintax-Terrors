import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AssignmentActions from '../../actions/entities/assignment.action';
import * as AssignmentSelectors from '../../selectors/entities/assignment-state.selectors';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { CreateAssignmentsCmd } from '../../../commands/entitites/assignments/create-assignments.cmd';

@Injectable()
export class CreateAssignmentsEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _createAssignmentsCmd: CreateAssignmentsCmd
  ) {}

  createAssignment$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssignmentActions.createAssignment),
      switchMap(action => {
        return this._createAssignmentsCmd.execute(action.assignment);
      }),
      switchMap(response => {
        return this._store$
          .select(AssignmentSelectors.selectPagination)
          .pipe(first());
      }),
      concatMap(params => {
        return [
          AssignmentActions.loadAssignments({
            params,
          }),
        ];
      })
    )
  );
}
