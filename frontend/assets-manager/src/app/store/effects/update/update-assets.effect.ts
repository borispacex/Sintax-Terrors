import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AssetsActions from '../../actions/entities/asset.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateAssetsCmd } from '../../../commands/entitites/assets/update-assets.cmd';

@Injectable()
export class UpdateAssetsEffect {
  constructor(
    private _actions$: Actions,
    private _updateAssetCmd: UpdateAssetsCmd
  ) {}

  updateAsset$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssetsActions.updateAsset),
      switchMap(action => {
        return this._updateAssetCmd.execute(action.asset);
      }),
      concatMap(response => {
        return [
          AssetsActions.updateAssetSuccess({
            asset: response.content,
          }),
        ];
      })
    )
  );
}
