import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { categoryEntityAdapter } from '../../states/entities/category-state';
import { Category } from '../../../pages/asset/interfaces/category.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectCategoriesState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.categories
);

export const categorySelectors = categoryEntityAdapter.getSelectors(
  selectCategoriesState
);

export const selectEntities = createSelector(
  categorySelectors.selectAll,
  categories => categories
);

export const selectCategoryData = createSelector(
  selectCategoriesState,
  state => ({
    entities: Object.values(state.entities) as Category[],
    pagination: state.pagination,
    isLoading: state.loading,
    params: state.params,
  })
);

export const selectIsLoading = createSelector(
  selectCategoriesState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectCategoriesState,
  state => state.pagination
);

export const selectParams = createSelector(
  selectCategoriesState,
  state => state.params
);
