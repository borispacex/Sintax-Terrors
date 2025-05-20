import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AssetsActions from '../../actions/entities/asset.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as AssetSelectors from '../../selectors/entities/asset-state.selectors';
import { Store } from '@ngrx/store';
import { CreateAssetsCmd } from '../../../commands/entitites/assets/create-assets.cmd';

@Injectable()
export class CreateAssetsEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _createAssetsCmd: CreateAssetsCmd
  ) {}

  createAsset$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssetsActions.createAsset),
      switchMap(action => {
        return this._createAssetsCmd.execute(action.asset);
      }),
      switchMap(response => {
        return this._store$
          .select(AssetSelectors.selectPagination)
          .pipe(first());
      }),
      concatMap(params => {
        return [
          AssetsActions.loadAssets({
            params,
          }),
        ];
      })
    )
  );
}
