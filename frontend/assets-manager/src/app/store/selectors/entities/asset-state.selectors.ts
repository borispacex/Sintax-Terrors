import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { assetEntityAdapter } from '../../states/entities/asset-state';
import { Asset } from '../../../pages/asset/interfaces/asset.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectAssetsState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.assets
);

export const assetSelectors =
  assetEntityAdapter.getSelectors(selectAssetsState);

export const selectAssetData = createSelector(selectAssetsState, state => ({
  entities: Object.values(state.entities) as Asset[],
  pagination: state.pagination,
  isLoading: state.loading,
  params: state.params,
}));

export const selectEntities = createSelector(
  assetSelectors.selectAll,
  assets => assets
);

export const selectIsLoading = createSelector(
  selectAssetsState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectAssetsState,
  state => state.pagination
);
