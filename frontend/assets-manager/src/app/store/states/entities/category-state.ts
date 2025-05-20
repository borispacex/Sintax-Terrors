import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Category } from '../../../pages/asset/interfaces/category.interface';
export interface CategoryState extends EntityState<Category> {
  pagination: PaginationMetaData;
  params: RequestParams;
  loading: boolean;
}

export const categoryEntityAdapter = createEntityAdapter<Category>({
  selectId: (entity: Category) => entity.id,
  sortComparer: false,
});

export const initialCategoryState: CategoryState =
  categoryEntityAdapter.getInitialState({
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
