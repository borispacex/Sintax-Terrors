import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as EmployeesActions from '../../actions/entities/employee.action';
import { concatMap, map, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateEmployeeCmd } from '../../../commands/entitites/employees/update-employee.cmd';
import {
  CreateEmployeeRequest,
  UpdateEmployeeRequest,
} from '../../../pages/employee/interfaces/employee.interface';
import { of } from 'rxjs';
import { FirebaseStorageService } from '../../../services/firebase-storage.service';

@Injectable()
export class UpdateEmployeeEffect {
  constructor(
    private readonly _actions$: Actions,
    private readonly _firebaseStorageService: FirebaseStorageService,
    private readonly _updateEmployeeCmd: UpdateEmployeeCmd
  ) {}

  updateEmployee$ = createEffect(() =>
    this._actions$.pipe(
      ofType(EmployeesActions.updateEmployee),
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
                } as UpdateEmployeeRequest,
              }))
            );
        } else {
          return of(action);
        }
      }),
      concatMap(action => {
        return this._updateEmployeeCmd.execute(action.employee);
      }),
      concatMap(response => {
        return [
          EmployeesActions.updateEmployeeSuccess({
            employee: response.content,
          }),
        ];
      })
    )
  );
}
