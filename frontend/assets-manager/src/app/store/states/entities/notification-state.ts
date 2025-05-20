import { createEntityAdapter, EntityState } from '@ngrx/entity';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Notification } from '../../../modules/shared/notification/interface/notification.interface';

export interface NotificationState extends EntityState<Notification> {
  pagination: PaginationMetaData;
  loading: boolean;
  params: RequestParams;
}

export const notificationEntityAdapter = createEntityAdapter<Notification>({
  selectId: (entity: Notification) => entity.id,
  sortComparer: false,
});

export const initialNotificationState: NotificationState =
  notificationEntityAdapter.getInitialState({
    ids: [],
    entities: [],
    pagination: {
      totalPages: 0,
      pageSize: 0,
      totalItems: 0,
      currentPage: 0,
    },
    loading: false,
    params: {},
  });
