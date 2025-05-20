import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { AssignmentEmployee } from '../../../pages/assignment/interfaces/assignment-employee.interface';

export interface AssignmentState extends EntityState<AssignmentEmployee> {
  pagination: PaginationMetaData;
  params: RequestParams;
  loading: boolean;
}

export const assignmentEntityAdapter = createEntityAdapter<AssignmentEmployee>({
  selectId: (entity: AssignmentEmployee) => entity.id,
  sortComparer: false,
});

export const initialAssignmentState: AssignmentState =
  assignmentEntityAdapter.getInitialState({
    ids: [],
    entities: [],
    pagination: {
      totalPages: 0,
      pageSize: 0,
      totalItems: 0,
      currentPage: 0,
    },
    params: {},
    loading: false,
  });
