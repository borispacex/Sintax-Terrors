import { Store } from '@ngrx/store';
import * as AssetSelectors from '../../../store/selectors/entities/asset-state.selectors';
import { Observable } from 'rxjs';
import {
  Asset,
  AssetData,
  CreateAssetRequest,
  UpdateAssetRequest,
} from '../interfaces/asset.interface';
import * as AssetActions from '../../../store/actions/entities/asset.action';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Injectable } from '@angular/core';

@Injectable()
export class AssetFacade {
  constructor(private _store: Store) {}

  public getAssetsData(): Observable<AssetData> {
    return this._store.select(AssetSelectors.selectAssetData);
  }

  public getAssets$(): Observable<Asset[]> {
    return this._store.select(AssetSelectors.selectEntities);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(AssetSelectors.selectPagination);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(AssetSelectors.selectIsLoading);
  }

  public loadAssets(params: RequestParams): void {
    this._store.dispatch(AssetActions.loadAssets({ params }));
  }

  public updateAsset(request: UpdateAssetRequest): void {
    this._store.dispatch(AssetActions.updateAsset({ asset: request }));
  }

  public deleteAsset(id: number): void {
    this._store.dispatch(AssetActions.deleteAsset({ id }));
  }

  public createAsset(request: CreateAssetRequest): void {
    this._store.dispatch(AssetActions.createAsset({ asset: request }));
  }
}
