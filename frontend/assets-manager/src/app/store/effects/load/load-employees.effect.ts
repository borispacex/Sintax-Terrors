import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as EmployeesActions from '../../actions/entities/employee.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { LoadEmployeesCmd } from '../../../commands/entitites/employees/load-employees.cmd';
import { Injectable } from '@angular/core';

@Injectable()
export class LoadEmployeesEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _loadEmployeesCmd: LoadEmployeesCmd
  ) {}

  loadEmployees$ = createEffect(() =>
    this._actions$.pipe(
      ofType(EmployeesActions.loadEmployees),
      switchMap(action => {
        return this._loadEmployeesCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          EmployeesActions.loadEmployeesSuccess({
            employees: response.content.items,
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
