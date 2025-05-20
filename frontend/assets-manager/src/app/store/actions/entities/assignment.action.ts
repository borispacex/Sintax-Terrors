import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  AssignmentEmployee,
  CreateAssignmentRequest,
  UpdateAssignmentRequest,
} from '../../../pages/assignment/interfaces/assignment-employee.interface';

export const loadAssignments = createAction(
  '[Assignment] Load Assignments',
  props<{ params: RequestParams }>()
);

export const loadAssignmentsSuccess = createAction(
  '[Assignment] Load Assignments Success',
  props<{ assignments: AssignmentEmployee[]; pagination: PaginationMetaData }>()
);

export const updateAssignment = createAction(
  '[Assignment] Update Assignments',
  props<{ assignment: UpdateAssignmentRequest }>()
);

export const updateAssignmentSuccess = createAction(
  '[Assignment] Update Assignments Success',
  props<{ assignment: AssignmentEmployee }>()
);

export const deleteAssignment = createAction(
  '[Assignment] Remove Assignment',
  props<{ assignment: CreateAssignmentRequest }>()
);

export const deleteAssignmentSuccess = createAction(
  '[Assignment] Remove Assignment Success',
  props<{ id: number }>()
);

export const createAssignment = createAction(
  '[Assignment] Create Assignment',
  props<{ assignment: CreateAssignmentRequest }>()
);

export const createAssignmentSuccess = createAction(
  '[Assignment] Create Assignment Success',
  props<{ assignment: AssignmentEmployee }>()
);

export const updateParams = createAction(
  '[Assignment] Update Params',
  props<{ params: RequestParams }>()
);
