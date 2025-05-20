import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as WorkspacesActions from '../../actions/entities/workspace.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateWorkspacesCmd } from '../../../commands/entitites/workspaces/update-workspaces.cmd';

@Injectable()
export class UpdateWorkspacesEffect {
  constructor(
    private _actions$: Actions,
    private _updateWorkspaceCmd: UpdateWorkspacesCmd
  ) {}

  updateWorkspace$ = createEffect(() =>
    this._actions$.pipe(
      ofType(WorkspacesActions.updateWorkspace),
      switchMap(action => {
        return this._updateWorkspaceCmd.execute(action.workspace);
      }),
      concatMap(response => {
        return [
          WorkspacesActions.updateWorkspaceSuccess({
            workspace: response.content,
          }),
        ];
      })
    )
  );
}
