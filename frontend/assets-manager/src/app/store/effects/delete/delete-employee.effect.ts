import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as EmployeesActions from '../../actions/entities/employee.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { DeleteEmployeeCmd } from '../../../commands/entitites/employees/delete-employee.cmd';
import * as EmployeeSelectors from '../../selectors/entities/employee-state.selectors';
import { Store } from '@ngrx/store';

@Injectable()
export class DeleteEmployeeEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _store$: Store,
    private readonly _deleteEmployeeCmd: DeleteEmployeeCmd
  ) {}

  deleteEmployee$ = createEffect(() =>
    this._actions$.pipe(
      ofType(EmployeesActions.deleteEmployee),
      switchMap(action => {
        return this._deleteEmployeeCmd.execute(action.id);
      }),
      switchMap(response => {
        return this._store$
          .select(EmployeeSelectors.selectParams)
          .pipe(first());
      }),
      concatMap(params => {
        return [
          EmployeesActions.loadEmployees({
            params,
          }),
        ];
      })
    )
  );
}
