import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { employeeEntityAdapter } from '../../states/entities/employee-state';
import { Employee } from '../../../pages/employee/interfaces/employee.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectEmployeesState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.employees
);

export const employeeSelectors =
  employeeEntityAdapter.getSelectors(selectEmployeesState);

export const selectEmployeeData = createSelector(
  selectEmployeesState,
  state => ({
    entities: Object.values(state.entities) as Employee[],
    pagination: state.pagination,
    isLoading: state.loading,
    params: state.params,
  })
);

export const selectEntities = createSelector(
  employeeSelectors.selectAll,
  employees => employees
);

export const selectIsLoading = createSelector(
  selectEmployeesState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectEmployeesState,
  state => state.pagination
);

export const selectParams = createSelector(
  selectEmployeesState,
  state => state.params
);
