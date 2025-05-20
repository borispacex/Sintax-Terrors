import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AssignmentActions from '../../actions/entities/assignment.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateAssignmentsCmd } from '../../../commands/entitites/assignments/update-assignments.cmd';

@Injectable()
export class UpdateAssignmentsEffect {
  constructor(
    private _actions$: Actions,
    private _updateAssignmentsCmd: UpdateAssignmentsCmd
  ) {}

  updateWorkspace$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssignmentActions.updateAssignment),
      switchMap(action => {
        return this._updateAssignmentsCmd.execute(action.assignment);
      }),
      concatMap(response => {
        return [
          AssignmentActions.updateAssignmentSuccess({
            assignment: response.content,
          }),
        ];
      })
    )
  );
}
