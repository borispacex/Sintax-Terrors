import {
  ChangeDetectionStrategy,
  Component,
  OnInit,
  TemplateRef,
} from '@angular/core';
import { ServerDataTableHeader } from '../../modules/shared/server-data-table/interfaces/server-data-table.interface';
import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';
import { Observable, of } from 'rxjs';
import { PaginationResponse } from '../../interfaces/common-response/pagination-response.interface';
import { ModalService } from '../../modules/shared/modal/services/modal.service';
import { map, tap } from 'rxjs/operators';
import { TeamFacade } from './facades/team.facade';
import { Team, TeamTable } from './interfaces/team.interface';
import { teamTableHeaders } from './helpers/team-table.config';
import { initialActiveTeam } from './helpers/initial-team-variables.helper';
import { TeamTableBuilder } from './builders/team-table-items.builder';

@Component({
  selector: 'am-team-table',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TeamComponent implements OnInit {
  public tableHeaders: ServerDataTableHeader[] = teamTableHeaders;
  public tableData$: Observable<PaginationResponse<TeamTable>> = of({
    items: [],
    totalItems: 0,
    currentPage: 1,
    pageSize: 10,
    totalPages: 1,
  });
  public isLoading: boolean = false;
  public activeTeam: Team = initialActiveTeam;

  private _params: RequestParams = {
    page: 1,
    size: 10,
  };

  constructor(
    private readonly _modalService: ModalService,
    private readonly _teamFacade: TeamFacade
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  public onPageChange(page: number): void {
    this._teamFacade.loadTeams({ ...this._params, page });
  }

  public onSortChange(event: {
    field: string;
    direction: 'asc' | 'desc';
  }): void {
    this._teamFacade.loadTeams({
      ...this._params,
      direction: event.direction.toUpperCase(),
      order: event.field.toUpperCase(),
    });
  }

  public onRowsPerPageChange(size: number): void {
    this._teamFacade.loadTeams({ ...this._params, size });
  }

  public openCreateModal(modalTemplate: TemplateRef<any>): void {
    const modalOptions = {
      size: 'md',
      scrollable: true,
      tittle: 'Create new team',
    };
    this._modalService.open(modalTemplate, modalOptions);
  }

  public onRemoveItem(item: TeamTable) {
    this._teamFacade.deleteTeam(item.team.id);
  }

  public openUpdateModal(item: TeamTable, updateModal: TemplateRef<any>): void {
    this.activeTeam = { ...item.team };
    this._modalService.open(updateModal, {
      size: 'md',
      scrollable: true,
      tittle: 'Update team',
    });
  }

  private _initialize(): void {
    this._teamFacade.loadTeams(this._params);

    this.tableData$ = this._teamFacade.getTeamData$().pipe(
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
          items: TeamTableBuilder.build(entities),
          ...pagination,
        };
      })
    );
  }
}
