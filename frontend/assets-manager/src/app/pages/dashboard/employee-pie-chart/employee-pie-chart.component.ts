import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { PaginationResponse } from '../../../interfaces/common-response/pagination-response.interface';
import { EmployeeTable } from '../../employee/interfaces/employee.interface';
import { EmployeeFacade } from '../../employee/facades/employee.facade';
import { EmployeeTableBuilder } from '../../employee/builders/employee-table-items.builder';
import { initialEmployeeTableData } from '../../employee/helpers/initial-employee-variables.helper';

@Component({
  selector: 'am-employee-pie-chart',
  templateUrl: './employee-pie-chart.component.html',
  styleUrls: ['./employee-pie-chart.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmployeePieChartComponent implements OnInit {
  public params: RequestParams = {
    page: 1,
    size: 50,
  };

  public employeeData$: Observable<PaginationResponse<EmployeeTable>> = of(
    initialEmployeeTableData
  );
  public processedData: EmployeeTable[] = [];
  public isLoading = false;
  public pieChartLabels: Label[] = [];
  public pieChartData: number[] = [];
  public pieChartType: ChartType = 'pie';

  public pieChartColors = [
    {
      backgroundColor: ['#20bf86', '#496ef1', '#e075d5', '#cdb14f', '#e07b75'], //TODO Set colors with variables
      borderColor: '#fff',
      borderWidth: 2,
    },
  ];

  public pieChartOptions: ChartOptions = {
    responsive: true,
    title: {
      display: true,
      text: 'Employee status',
      fontSize: 16,
      padding: 20,
    },
  };

  constructor(private _employeeFacade: EmployeeFacade) {}

  ngOnInit(): void {
    this._initialize();
  }

  private _initialize(): void {
    this._employeeFacade.loadEmployees(this.params);

    this.employeeData$ = this._employeeFacade.getEmployeesData$().pipe(
      tap(({ pagination, isLoading }) => {
        this.params = {
          ...this.params,
          page: pagination.currentPage,
          size: pagination.pageSize,
        };
        this.isLoading = isLoading;
      }),
      map(({ entities, pagination }) => ({
        items: EmployeeTableBuilder.build(entities),
        ...pagination,
      })),
      tap(data => {
        this.processedData = data.items;
        this._processDataForCharts();
      })
    );
  }

  private _processDataForCharts(): void {
    this.pieChartLabels = [];
    this.pieChartData = [];
    const statusCount: Record<string, number> = {};

    this.processedData.forEach(employee => {
      if (employee.status) {
        statusCount[employee.status] = (statusCount[employee.status] || 0) + 1;
      }
    });

    Object.entries(statusCount).forEach(([status, count]) => {
      this.pieChartLabels.push(status);
      this.pieChartData.push(count);
    });
  }
}
