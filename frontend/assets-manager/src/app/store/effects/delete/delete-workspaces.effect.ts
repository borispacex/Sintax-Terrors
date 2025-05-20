import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as WorkspacesActions from '../../actions/entities/workspace.action';
import { concatMap, first, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { DeleteWorkspacesCmd } from '../../../commands/entitites/workspaces/delete-workspaces.cmd';
import * as WorkspaceSelectors from '../../selectors/entities/workspace-state.selectors';
import { Store } from '@ngrx/store';

@Injectable()
export class DeleteWorkspacesEffect {
  constructor(
    private _actions$: Actions,
    private _store$: Store,
    private _deleteWorkspacesCmd: DeleteWorkspacesCmd
  ) {}

  deleteWorkspace$ = createEffect(() =>
    this._actions$.pipe(
      ofType(WorkspacesActions.deleteWorkspace),
      switchMap(action => {
        return this._deleteWorkspacesCmd.execute(action.id);
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
