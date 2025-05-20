import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as EmployeesActions from '../../actions/entities/employee.action';
import { concatMap, first, map, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as EmployeeSelectors from '../../selectors/entities/employee-state.selectors';
import { Store } from '@ngrx/store';
import { CreateEmployeeCmd } from '../../../commands/entitites/employees/create-employee.cmd';
import { FirebaseStorageService } from '../../../services/firebase-storage.service';
import { CreateEmployeeRequest } from '../../../pages/employee/interfaces/employee.interface';
import { of } from 'rxjs';

@Injectable()
export class CreateEmployeeEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _store$: Store,
    private readonly _firebaseStorageService: FirebaseStorageService,
    private readonly _createEmployeeCmd: CreateEmployeeCmd
  ) {}

  createEmployee$ = createEffect(() =>
    this._actions$.pipe(
      ofType(EmployeesActions.createEmployee),
      switchMap(action => {
        if (action.employee.image) {
          return this._firebaseStorageService
            .uploadFile$(action.employee.image)
            .pipe(
              map(url => ({
                ...action,
                employee: {
                  ...action.employee,
                  uploadedImageID: url,
                } as CreateEmployeeRequest,
              }))
            );
        } else {
          return of(action);
        }
      }),
      concatMap(action => {
        return this._createEmployeeCmd.execute(action.employee);
      }),
      concatMap(response => {
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
