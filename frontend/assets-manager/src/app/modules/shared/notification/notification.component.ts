import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { combineLatest, Observable, of } from 'rxjs';
import { PaginationResponse } from '../../../interfaces/common-response/pagination-response.interface';
import { Notification } from './interface/notification.interface';
import { NotificationFacade } from './facades/notification.facade';
import { map, tap } from 'rxjs/operators';
import { UserFacade } from '../profile/facades/user.facade';
import { UserInterface } from '../profile/interfaces/user.interface';
import {
  initialActiveUser,
  initialNotificationTableData,
} from './helpers/initial-notification-variables.helper';

@Component({
  selector: 'am-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NotificationComponent implements OnInit {
  public notifications$: Observable<PaginationResponse<Notification>> = of(
    initialNotificationTableData
  );
  public isLoading: boolean = false;
  public showNotificationMenu: boolean = false;

  private _params: RequestParams = {
    isRead: false,
  };

  constructor(
    private _notificationFacade: NotificationFacade,
    private _userFacade: UserFacade
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  private _initialize(): void {
    this._notificationFacade.loadNotifications(this._params);
    const prevUser: UserInterface = initialActiveUser;

    this.notifications$ = combineLatest([
      this._notificationFacade.getNotificationsData$(),
      this._userFacade.getUser$(),
    ]).pipe(
      tap(([notificationData, user]) => {
        if (user?.id && user.id !== prevUser.id) {
          this._notificationFacade.loadNotifications({
            ...this._params,
            userId: user.id,
          });
        }
      }),
      map(([notificationData, user]) => notificationData),
      tap(({ pagination, isLoading }) => {
        this._params = {
          ...this._params,
          page: pagination.currentPage,
          size: pagination.pageSize,
        };
        this.isLoading = isLoading;
      }),
      map(({ entities, pagination }) => {
        return {
          items: entities,
          ...this._params,
        } as PaginationResponse<Notification>;
      })
    );
  }

  public toggleNotifications(): void {
    this.showNotificationMenu = !this.showNotificationMenu;
  }

  public trackById(index: number, item: Notification): number {
    return item.id;
  }
}
