import { Component, OnInit, TemplateRef } from '@angular/core';
import { ServerDataTableHeader } from '../../modules/shared/server-data-table/interfaces/server-data-table.interface';
import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';
import { Observable, of } from 'rxjs';
import { PaginationResponse } from '../../interfaces/common-response/pagination-response.interface';
import { ModalService } from '../../modules/shared/modal/services/modal.service';
import { map, tap } from 'rxjs/operators';
import { EmployeeTableBuilder } from './builders/employee-table-items.builder';
import { employeeTableHeaders } from './helpers/employee-table.config';
import {
  initialActiveEmployee,
  initialEmployeeTableData,
} from './helpers/initial-employee-variables.helper';
import { EmployeeFacade } from './facades/employee.facade';
import { Employee, EmployeeTable } from './interfaces/employee.interface';

@Component({
  selector: 'am-employee-table',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss'],
})
export class EmployeeComponent implements OnInit {
  public tableHeaders: ServerDataTableHeader[] = employeeTableHeaders;
  public tableData$: Observable<PaginationResponse<EmployeeTable>> = of(
    initialEmployeeTableData
  );
  public isLoading: boolean = false;
  public activeEmployee: Employee = initialActiveEmployee;

  private _params: RequestParams = {
    page: 1,
    size: 10,
  };

  constructor(
    private readonly _modalService: ModalService,
    private readonly _employeeFacade: EmployeeFacade
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  public onPageChange(page: number): void {
    this._employeeFacade.loadEmployees({ ...this._params, page });
  }

  public onSortChange(event: {
    field: string;
    direction: 'asc' | 'desc';
  }): void {
    this._employeeFacade.loadEmployees({
      ...this._params,
      direction: event.direction.toUpperCase(),
      order: event.field.toUpperCase(),
    });
  }

  public onRowsPerPageChange(size: number): void {
    this._employeeFacade.loadEmployees({ ...this._params, size });
  }

  public openCreateModal(modalTemplate: TemplateRef<any>): void {
    const modalOptions = {
      size: 'lg',
      scrollable: true,
      tittle: 'Create new employee',
    };
    this._modalService.open(modalTemplate, modalOptions);
  }

  public onRemoveItem(item: EmployeeTable) {
    this._employeeFacade.deleteEmployee(item.employee.id);
  }

  public openUpdateModal(
    item: EmployeeTable,
    updateModal: TemplateRef<any>
  ): void {
    this.activeEmployee = { ...item.employee };
    this._modalService.open(updateModal, {
      size: 'lg',
      scrollable: true,
      tittle: 'Update team',
    });
  }

  private _initialize(): void {
    this._employeeFacade.loadEmployees(this._params);

    this.tableData$ = this._employeeFacade.getEmployeesData$().pipe(
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
          items: EmployeeTableBuilder.build(entities),
          ...pagination,
        };
      })
    );
  }
}
