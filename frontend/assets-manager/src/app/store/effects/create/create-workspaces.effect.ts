import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as WorkspacesActions from '../../actions/entities/workspace.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import * as WorkspaceSelectors from '../../selectors/entities/workspace-state.selectors';
import { Store } from '@ngrx/store';
import { CreateWorkspacesCmd } from '../../../commands/entitites/workspaces/create-workspaces.cmd';

@Injectable()
export class CreateWorkspacesEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _createWorkspacesCmd: CreateWorkspacesCmd
  ) {}

  createWorkspace$ = createEffect(() =>
    this._actions$.pipe(
      ofType(WorkspacesActions.createWorkspace),
      switchMap(action => {
        return this._createWorkspacesCmd.execute(action.workspace);
      }),
      switchMap(response => {
        return this._store$
          .select(WorkspaceSelectors.selectParams)
          .pipe(first());
      }),
      concatMap(params => {
        return [
          WorkspacesActions.loadWorkspaces({
            params,
          }),
        ];
      })
    )
  );
}
