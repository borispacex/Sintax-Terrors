import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as NotificationsActions from '../../actions/entities/notification.action';
import { concatMap, switchMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { UpdateNotificationsCmd } from '../../../commands/entitites/notifications/update-notifications.cmd';

@Injectable()
export class UpdateNotificationsEffect {
  constructor(
    private _actions$: Actions,
    private _updateNotificationsCmd: UpdateNotificationsCmd
  ) {}

  updateNotification$ = createEffect(() =>
    this._actions$.pipe(
      ofType(NotificationsActions.updateNotification),
      switchMap(action => {
        return this._updateNotificationsCmd.execute(action.notification);
      }),
      concatMap(response => {
        return [
          NotificationsActions.updateNotificationSuccess({
            notification: response.content,
          }),
        ];
      })
    )
  );
}
