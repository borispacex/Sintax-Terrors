import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Asset } from '../../../pages/asset/interfaces/asset.interface';

export interface AssetState extends EntityState<Asset> {
  pagination: PaginationMetaData;
  loading: boolean;
  params: RequestParams;
}

export const assetEntityAdapter = createEntityAdapter<Asset>({
  selectId: (entity: Asset) => entity.id,
  sortComparer: false,
});

export const initialAssetState: AssetState = assetEntityAdapter.getInitialState(
  {
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
  }
);
