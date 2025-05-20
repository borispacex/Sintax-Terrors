import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as CategoriesActions from '../../actions/entities/category.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { LoadCategoriesCmd } from '../../../commands/entitites/categories/load-categories.cmd';
import { Injectable } from '@angular/core';

@Injectable()
export class LoadCategoriesEffect {
  constructor(
    private actions$: Actions,
    private loadCategoriesCmd: LoadCategoriesCmd
  ) {}

  loadCategories$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CategoriesActions.loadCategories),
      switchMap(action => {
        return this.loadCategoriesCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          CategoriesActions.loadCategoriesSuccess({
            categories: response.content.items,
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
