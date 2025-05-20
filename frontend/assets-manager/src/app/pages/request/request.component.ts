import {
  ChangeDetectionStrategy,
  Component,
  OnInit,
  TemplateRef,
} from '@angular/core';
import { ModalService } from '../../modules/shared/modal/services/modal.service';
import { ServerDataTableHeader } from '../../modules/shared/server-data-table/interfaces/server-data-table.interface';
import { Request, RequestTable } from './interfaces/request.interface';
import { PaginationResponse } from '../../interfaces/common-response/pagination-response.interface';
import { RequestFacade } from './facades/request.facade';
import { Observable, of } from 'rxjs';
import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';
import { map, tap } from 'rxjs/operators';
import { requestTableHeaders } from './helpers/request-table.config';
import {
  initialActiveRequest,
  initialRequestTableData,
} from './helpers/initial-request-variables.helper';
import { RequestTableBuilder } from './builders/request-table-items.builder';

@Component({
  selector: 'am-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RequestComponent implements OnInit {
  public tableData$: Observable<PaginationResponse<RequestTable>> = of(
    initialRequestTableData
  );
  public tableHeaders: ServerDataTableHeader[] = requestTableHeaders;
  public activeRequest: Request = initialActiveRequest;
  public isLoading: boolean = false;

  private _params: RequestParams = {
    page: 1,
    size: 10,
  };

  constructor(
    private _modalService: ModalService,
    private _requestFacade: RequestFacade
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  public openModal(modalTemplate: TemplateRef<any>): void {
    const modalOptions = {
      size: 'md',
      scrollable: true,
      tittle: 'New Request',
    };
    this._modalService.open(modalTemplate, modalOptions);
  }

  public onUpdateItem(item: RequestTable, updateModal: TemplateRef<any>): void {
    this.activeRequest = { ...item.request };
    this._modalService.open(updateModal, {
      size: 'md',
      scrollable: true,
      tittle: 'Update Request',
    });
  }

  public onPageChange(page: number): void {
    this._requestFacade.loadRequests({ ...this._params, page });
  }

  public onSortChange(event: {
    field: string;
    direction: 'asc' | 'desc';
  }): void {
    this._requestFacade.loadRequests({
      ...this._params,
      direction: event.direction.toUpperCase(),
      order: event.field.toUpperCase(),
    });
  }

  public onRowsPerPageChange(size: number): void {
    this._requestFacade.loadRequests({ ...this._params, size });
  }

  public onRemoveItem(item: RequestTable) {
    this._requestFacade.deleteRequest(item.request.id);
  }

  private _initialize(): void {
    this._requestFacade.loadRequests(this._params);

    this.tableData$ = this._requestFacade.getRequestsData$().pipe(
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
          items: RequestTableBuilder.build(entities),
          ...pagination,
        };
      })
    );
  }
}
