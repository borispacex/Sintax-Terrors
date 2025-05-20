import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CollectionState } from '../../states/collection-state';
import { notificationEntityAdapter } from '../../states/entities/notification-state';
import { Notification } from '../../../modules/shared/notification/interface/notification.interface';

export const selectEntityState =
  createFeatureSelector<CollectionState>('entityState');

export const selectNotificationsState = createSelector(
  selectEntityState,
  (state: CollectionState) => state.notifications
);

export const notificationSelectors = notificationEntityAdapter.getSelectors(
  selectNotificationsState
);

export const selectEntities = createSelector(
  notificationSelectors.selectAll,
  notifications => notifications
);

export const selectNotificationData = createSelector(
  selectNotificationsState,
  state => ({
    entities: Object.values(state.entities) as Notification[],
    pagination: state.pagination,
    isLoading: state.loading,
    params: state.params,
  })
);

export const selectIsLoading = createSelector(
  selectNotificationsState,
  state => state.loading
);

export const selectPagination = createSelector(
  selectNotificationsState,
  state => state.pagination
);
