import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as NotificationsActions from '../../actions/entities/notification.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { LoadNotificationsCmd } from '../../../commands/entitites/notifications/load-notifications.cmd';

@Injectable()
export class LoadNotificationsEffect {
  constructor(
    private _actions$: Actions,
    private _loadNotificationsCmd: LoadNotificationsCmd
  ) {}

  loadNotifications$ = createEffect(() =>
    this._actions$.pipe(
      ofType(NotificationsActions.loadNotifications),
      switchMap(action => {
        return this._loadNotificationsCmd.execute(action.params);
      }),
      concatMap(response => {
        return [
          NotificationsActions.loadNotificationsSuccess({
            notifications: response.content.items,
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
