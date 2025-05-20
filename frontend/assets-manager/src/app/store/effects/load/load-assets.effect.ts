import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AssetsActions from '../../actions/entities/asset.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { LoadAssetsCmd } from '../../../commands/entitites/assets/load-assets.cmd';

@Injectable()
export class LoadAssetsEffect {
  constructor(
    private _actions$: Actions,
    private _loadAssetsCmd: LoadAssetsCmd
  ) {}

  loadAssets$ = createEffect(() =>
    this._actions$.pipe(
      ofType(AssetsActions.loadAssets),
      switchMap(action => {
        return this._loadAssetsCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          AssetsActions.loadAssetsSuccess({
            assets: response.content.items,
            pagination: {
              totalItems: response.content.totalItems,
              currentPage: response.content.currentPage,
              pageSize: response.content.pageSize,
              totalPages: response.content.totalPages,
            },
          }),
        ];
      })
    )
  );
}
