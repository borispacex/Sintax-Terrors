import { Component, OnInit, TemplateRef } from '@angular/core';
import { ServerDataTableHeader } from '../../modules/shared/server-data-table/interfaces/server-data-table.interface';
import { Observable, of } from 'rxjs';
import { PaginationResponse } from '../../interfaces/common-response/pagination-response.interface';
import { ModalService } from '../../modules/shared/modal/services/modal.service';
import { map, tap } from 'rxjs/operators';
import { AssignmentEmployee } from './interfaces/assignment-employee.interface';
import { AssignmentFacade } from './facades/assignment.facade';
import { Employee } from '../employee/interfaces/employee.interface';
import { EmployeeBuilder } from './builders/employee.builder';
import { assignmentTableHeaders } from './helpers/assignment-table.config';
import { initialAssignmentTableData } from './helpers/initial-assignment-variables.helper';
import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';

@Component({
  selector: 'am-assignment',
  templateUrl: './assignment.component.html',
  styleUrls: ['./assignment.component.scss'],
})
export class AssignmentComponent implements OnInit {
  public tableHeaders: ServerDataTableHeader[] = assignmentTableHeaders;

  public tableData$: Observable<PaginationResponse<AssignmentEmployee>> = of(
    initialAssignmentTableData
  );
  public isLoading: boolean = false;
  public activeEmployee: Employee | null = null;

  private _params: RequestParams = {
    page: 1,
    size: 10,
  };

  constructor(
    private _modalService: ModalService,
    private _assignmentFacade: AssignmentFacade
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  public onPageChange(page: number): void {
    this._assignmentFacade.loadAssignments({ ...this._params, page });
  }

  public onSortChange(event: {
    field: string;
    direction: 'asc' | 'desc';
  }): void {
    this._assignmentFacade.loadAssignments({
      ...this._params,
      order: event.field.toUpperCase(),
      direction: event.direction.toUpperCase(),
    });
  }

  public onRowsPerPageChange(size: number): void {
    this._assignmentFacade.updateParams({ ...this._params, size });
  }

  public openModal(modalTemplate: TemplateRef<any>): void {
    const modalOptions = {
      size: 'md',
      scrollable: true,
      tittle: 'New assignment',
    };
    this._modalService.open(modalTemplate, modalOptions);
  }

  public onRemoveItem(
    item: AssignmentEmployee,
    deleteModal: TemplateRef<any>
  ): void {
    this.activeEmployee = EmployeeBuilder.build({ ...item });
    this._modalService.open(deleteModal, {
      size: 'md',
      scrollable: true,
      tittle: 'Update assignment',
    });
  }

  public onUpdateItem(
    item: AssignmentEmployee,
    updateModal: TemplateRef<any>
  ): void {
    this.activeEmployee = EmployeeBuilder.build({ ...item });
    this._modalService.open(updateModal, {
      size: 'md',
      scrollable: true,
      tittle: 'Update assignment',
    });
  }

  private _initialize(): void {
    this._assignmentFacade.loadAssignments(this._params);

    this.tableData$ = this._assignmentFacade.getAssignmentsData$().pipe(
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
