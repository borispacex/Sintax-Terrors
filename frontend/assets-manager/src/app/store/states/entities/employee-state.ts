import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Employee } from '../../../pages/employee/interfaces/employee.interface';

export interface EmployeeState extends EntityState<Employee> {
  pagination: PaginationMetaData;
  loading: boolean;
  params: RequestParams;
}

export const employeeEntityAdapter = createEntityAdapter<Employee>({
  selectId: (entity: Employee) => entity.id,
  sortComparer: false,
});

export const initialEmployeeState: EmployeeState =
  employeeEntityAdapter.getInitialState({
    ids: [],
    entities: [],
    pagination: {
      totalPages: 0,
      pageSize: 0,
      totalItems: 0,
      currentPage: 0,
    },
    loading: false,
    params: {},
  });
