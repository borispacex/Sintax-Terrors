import {
  ChangeDetectionStrategy,
  Component,
  OnInit,
  TemplateRef,
} from '@angular/core';
import { ServerDataTableHeader } from '../../modules/shared/server-data-table/interfaces/server-data-table.interface';
import { Observable, of } from 'rxjs';
import { PaginationResponse } from '../../interfaces/common-response/pagination-response.interface';
import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';
import { ModalService } from '../../modules/shared/modal/services/modal.service';
import { map, tap } from 'rxjs/operators';
import { UsersFacade } from './facades/users.facade';
import { User } from './interfaces/user.interface';
import { usersTableHeaders } from './helpers/users-table.config';
import { initialUsersTableData } from './helpers/initial-users-variables.helper';

@Component({
  selector: 'am-assignation-table',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class UserComponent implements OnInit {
  public tableHeaders: ServerDataTableHeader[] = usersTableHeaders;
  public tableData$: Observable<PaginationResponse<User>> = of(
    initialUsersTableData
  );
  public isLoading: boolean = false;
  public activeUser: User | null = null;

  private _params: RequestParams = {
    page: 1,
    size: 5,
  };

  constructor(
    private _modalService: ModalService,
    private _userFacade: UsersFacade
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  public onPageChange(page: number): void {
    this._userFacade.loadUsers({ ...this._params, page });
  }

  public onSortChange(event: {
    field: string;
    direction: 'asc' | 'desc';
  }): void {
    this._userFacade.loadUsers({
      ...this._params,
      order: event.field.toUpperCase(),
      direction: event.direction.toUpperCase(),
    });
  }

  public onRowsPerPageChange(size: number): void {
    this._userFacade.loadUsers({ ...this._params, size });
  }

  public openModal(modalTemplate: TemplateRef<any>): void {
    const modalOptions = {
      size: 'md',
      scrollable: true,
      tittle: 'Create new user',
    };
    this._modalService.open(modalTemplate, modalOptions);
  }

  public onUpdateItem(item: User, updateModal: TemplateRef<any>): void {
    this.activeUser = { ...item };
    this._modalService.open(updateModal, {
      size: 'md',
      scrollable: true,
      tittle: 'Update user',
    });
  }

  private _initialize(): void {
    this._userFacade.loadUsers(this._params);

    this.tableData$ = this._userFacade.getUsersData$().pipe(
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
          ...pagination,
        };
      })
    );
  }
}
