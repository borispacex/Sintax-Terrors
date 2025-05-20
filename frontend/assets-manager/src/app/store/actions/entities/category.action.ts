import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  Category,
  CreateCategoryRequest,
  UpdateCategoryRequest,
} from '../../../pages/asset/interfaces/category.interface';

export const loadCategories = createAction(
  '[Category] Load Categories',
  props<{ params: RequestParams }>()
);

export const loadCategoriesSuccess = createAction(
  '[Category] Load Categories Success',
  props<{ categories: Category[]; pagination: PaginationMetaData }>()
);

export const updateCategory = createAction(
  '[Category] Update Categories',
  props<{ category: UpdateCategoryRequest }>()
);

export const updateCategorySuccess = createAction(
  '[Category] Update Categories Success',
  props<{ category: Category }>()
);

export const deleteCategory = createAction(
  '[Category] Remove Category',
  props<{ id: number }>()
);

export const deleteCategorySuccess = createAction(
  '[Category] Remove Category Success',
  props<{ id: number }>()
);

export const createCategory = createAction(
  '[Category] Create Category',
  props<{ category: CreateCategoryRequest }>()
);

export const createCategorySuccess = createAction(
  '[Category] Create Category Success',
  props<{ category: Category }>()
);
