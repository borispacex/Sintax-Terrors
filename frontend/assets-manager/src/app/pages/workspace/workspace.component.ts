import {
  ChangeDetectionStrategy,
  Component,
  OnInit,
  TemplateRef,
} from '@angular/core';
import { Workspace } from './interfaces/workspace.interface';
import { PaginationResponse } from '../../interfaces/common-response/pagination-response.interface';
import { ModalService } from '../../modules/shared/modal/services/modal.service';
import { ServerDataTableHeader } from '../../modules/shared/server-data-table/interfaces/server-data-table.interface';
import { WorkspaceFacade } from './facades/workspace.facade';
import { Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';
import { workspaceTableHeaders } from './helpers/workspace-table.config';
import { initialWorkspaceTableData } from './helpers/initial-workspace-variables.helper';

@Component({
  selector: 'am-workspace',
  templateUrl: './workspace.component.html',
  styleUrls: ['./workspace.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class WorkspaceComponent implements OnInit {
  public tableHeaders: ServerDataTableHeader[] = workspaceTableHeaders;
  public tableData$: Observable<PaginationResponse<Workspace>> = of(
    initialWorkspaceTableData
  );
  public isLoading: boolean = false;
  public activeWorkspace: Workspace = {
    id: 0,
    city: '',
    location: '',
  };

  private _params: RequestParams = {
    page: 1,
    size: 5,
  };

  constructor(
    private _modalService: ModalService,
    private _workspaceFacade: WorkspaceFacade
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  public onPageChange(page: number): void {
    this._workspaceFacade.loadWorkspaces({ ...this._params, page });
  }

  public onSortChange(event: {
    field: string;
    direction: 'asc' | 'desc';
  }): void {
    this._workspaceFacade.loadWorkspaces({
      ...this._params,
      order: event.field.toUpperCase(),
      direction: event.direction.toUpperCase(),
    });
  }

  public onRowsPerPageChange(size: number): void {
    this._workspaceFacade.loadWorkspaces({ ...this._params, size });
  }

  public openModal(modalTemplate: TemplateRef<any>): void {
    const modalOptions = {
      size: 'md',
      scrollable: true,
      tittle: 'Create new workspace',
    };
    this._modalService.open(modalTemplate, modalOptions);
  }

  public onRemoveItem(item: Workspace): void {
    this._workspaceFacade.deleteWorkspace(item.id);
  }

  public onUpdateItem(item: Workspace, updateModal: TemplateRef<any>): void {
    this.activeWorkspace = { ...item };
    this._modalService.open(updateModal, {
      size: 'md',
      scrollable: true,
      tittle: 'Update workspace',
    });
  }

  private _initialize(): void {
    this._workspaceFacade.loadWorkspaces(this._params);

    this.tableData$ = this._workspaceFacade.getWorkspaceData$().pipe(
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
