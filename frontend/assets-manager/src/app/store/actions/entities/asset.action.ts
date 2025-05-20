import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  Asset,
  CreateAssetRequest,
  UpdateAssetRequest,
} from '../../../pages/asset/interfaces/asset.interface';

export const loadAssets = createAction(
  '[Asset] Load Assets',
  props<{ params: RequestParams }>()
);

export const loadAssetsSuccess = createAction(
  '[Asset] Load Assets Success',
  props<{ assets: Asset[]; pagination: PaginationMetaData }>()
);

export const updateAsset = createAction(
  '[Asset] Update Assets',
  props<{ asset: UpdateAssetRequest }>()
);

export const updateAssetSuccess = createAction(
  '[Asset] Update Assets Success',
  props<{ asset: Asset }>()
);

export const deleteAsset = createAction(
  '[Asset] Remove Asset',
  props<{ id: number }>()
);

export const deleteAssetSuccess = createAction(
  '[Asset] Remove Asset Success',
  props<{ id: number }>()
);

export const createAsset = createAction(
  '[Asset] Create Asset',
  props<{ asset: CreateAssetRequest }>()
);

export const createAssetSuccess = createAction(
  '[Asset] Create Asset Success',
  props<{ asset: Asset }>()
);
