import {
  PaginationMetaData,
  RequestParams,
} from '../../../../interfaces/common-response/pagination-meta-data.interface';

export interface Notification {
  id: number;
  userId: number;
  head: string;
  body: string;
  createdAt: Date;
  updatedAt: Date;
  isRead: boolean;
}

export interface NotificationData {
  entities: Notification[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface UpdateNotificationRequest {
  id: number;
  userId: number;
  head: string;
  body: string;
  createdAt: Date;
  isRead: boolean;
}
