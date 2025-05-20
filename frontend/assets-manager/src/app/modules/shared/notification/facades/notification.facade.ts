import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as NotificationActions from '../../../../store/actions/entities/notification.action';
import { Injectable } from '@angular/core';
import {
  Notification,
  NotificationData,
  UpdateNotificationRequest,
} from '../interface/notification.interface';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../../interfaces/common-response/pagination-meta-data.interface';
import * as NotificationSelectors from '../../../../store/selectors/entities/notification-state.selectors';

@Injectable()
export class NotificationFacade {
  constructor(private _store: Store) {}

  public getNotificationsData$(): Observable<NotificationData> {
    return this._store.select(NotificationSelectors.selectNotificationData);
  }

  public getNotifications$(): Observable<Notification[]> {
    return this._store.select(NotificationSelectors.selectEntities);
  }

  public getPagination$(): Observable<PaginationMetaData> {
    return this._store.select(NotificationSelectors.selectPagination);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(NotificationSelectors.selectIsLoading);
  }

  public loadNotifications(params: RequestParams): void {
    this._store.dispatch(NotificationActions.loadNotifications({ params }));
  }

  public updateNotification(request: UpdateNotificationRequest): void {
    this._store.dispatch(
      NotificationActions.updateNotification({ notification: request })
    );
  }
}
