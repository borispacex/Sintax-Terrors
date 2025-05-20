import { createAction, props } from '@ngrx/store';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import {
  Notification,
  UpdateNotificationRequest,
} from '../../../modules/shared/notification/interface/notification.interface';

export const loadNotifications = createAction(
  '[Notification] Load Notifications',
  props<{ params: RequestParams }>()
);

export const loadNotificationsSuccess = createAction(
  '[Notification] Load Notifications Success',
  props<{ notifications: Notification[]; pagination: PaginationMetaData }>()
);

export const updateNotification = createAction(
  '[Notification] Update Notifications',
  props<{ notification: UpdateNotificationRequest }>()
);

export const updateNotificationSuccess = createAction(
  '[Notification] Update Notifications Success',
  props<{ notification: Notification }>()
);
