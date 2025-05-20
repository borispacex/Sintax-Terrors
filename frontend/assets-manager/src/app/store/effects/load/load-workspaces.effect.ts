import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as WorkspacesActions from '../../actions/entities/workspace.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { LoadWorkspacesCmd } from '../../../commands/entitites/workspaces/load-workspaces.cmd';
import { Injectable } from '@angular/core';

@Injectable()
export class LoadWorkspacesEffect {
  constructor(
    private _actions$: Actions,
    private _loadWorkspacesCmd: LoadWorkspacesCmd
  ) {}

  loadWorkspaces$ = createEffect(() =>
    this._actions$.pipe(
      ofType(WorkspacesActions.loadWorkspaces),
      switchMap(action => {
        return this._loadWorkspacesCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          WorkspacesActions.loadWorkspacesSuccess({
            workspaces: response.content.items,
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
