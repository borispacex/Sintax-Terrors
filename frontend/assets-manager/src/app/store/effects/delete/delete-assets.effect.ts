import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AssetsActions from '../../actions/entities/asset.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as AssetSelectors from '../../selectors/entities/asset-state.selectors';
import { Store } from '@ngrx/store';
import { DeleteAssetsCmd } from '../../../commands/entitites/assets/delete-assets.cmd';

@Injectable()
export class DeleteAssetsEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _deleteAssetsCmd: DeleteAssetsCmd
  ) {}

  deleteAsset$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssetsActions.deleteAsset),
      switchMap(action => {
        return this._deleteAssetsCmd.execute(action.id);
      }),
      switchMap(response => {
        return this._store$
          .select(AssetSelectors.selectPagination)
          .pipe(first());
      }),
      concatMap(pagination => {
        return [
          AssetsActions.loadAssets({
            params: {
              page: pagination.currentPage,
              pageSize: pagination.pageSize,
            },
          }),
        ];
      })
    )
  );
}
