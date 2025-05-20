import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  CreateEmployeeRequest,
  Employee,
  UpdateEmployeeRequest,
} from '../../../pages/employee/interfaces/employee.interface';

export const loadEmployees = createAction(
  '[Employee] Load Employees',
  props<{ params: RequestParams }>()
);

export const loadEmployeesSuccess = createAction(
  '[Employee] Load Employees Success',
  props<{ employees: Employee[]; pagination: PaginationMetaData }>()
);

export const updateEmployee = createAction(
  '[Employee] Update Employees',
  props<{ employee: UpdateEmployeeRequest }>()
);

export const updateEmployeeSuccess = createAction(
  '[Employee] Update Employees Success',
  props<{ employee: Employee }>()
);

export const deleteEmployee = createAction(
  '[Employee] Remove Employee',
  props<{ id: number }>()
);

export const deleteEmployeeSuccess = createAction(
  '[Employee] Remove Employee Success',
  props<{ id: number }>()
);

export const createEmployee = createAction(
  '[Employee] Create Employee',
  props<{ employee: CreateEmployeeRequest }>()
);

export const createEmployeeSuccess = createAction(
  '[Employee] Create Employee Success',
  props<{ employee: Employee }>()
);
