import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import { PaginationResponse } from '../../../interfaces/common-response/pagination-response.interface';
import { initialAssetTableData } from '../../asset/helpers/initial-asset-variables.helper';
import { Asset } from '../../asset/interfaces/asset.interface';
import { AssetFacade } from '../../asset/facades/asset.facade';

@Component({
  selector: 'am-asset-bar-chart',
  templateUrl: './asset-bar-chart.component.html',
  styleUrls: ['./asset-bar-chart.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssetBarChartComponent implements OnInit {
  public params: RequestParams = {
    page: 1,
    size: 50,
  };

  public tableData$: Observable<PaginationResponse<Asset>> = of(
    initialAssetTableData
  );
  public processedData: Asset[] = [];
  public isLoading = false;
  public barChartLabels: Label[] = [];
  public barChartCounts: number[] = [];

  public barChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      yAxes: [{ ticks: { beginAtZero: true } }],
    },
    title: {
      display: true,
      text: 'Asset Distribution',
      fontSize: 16,
      padding: 20,
    },
    legend: {
      position: 'top',
    },
  };

  public barChartLegend = true;
  public barChartPlugins = [];
  public barChartData: ChartDataSets[] = [];
  public barChartType: ChartType = 'bar';

  constructor(private _assetFacade: AssetFacade) {}

  ngOnInit(): void {
    this._initialize();
  }

  private _initialize(): void {
    this._assetFacade.loadAssets(this.params);

    this.tableData$ = this._assetFacade.getAssetsData().pipe(
      tap(({ pagination, isLoading }) => {
        this.params = {
          ...this.params,
          page: pagination.currentPage,
          size: pagination.pageSize,
        };
        this.isLoading = isLoading;
      }),
      map(({ entities, pagination }) => ({
        items: entities,
        ...pagination,
      })),
      tap(data => {
        this.processedData = data.items;
        this._processDataForBar();
      })
    );
  }

  private _processDataForBar(): void {
    this.barChartLabels = [];
    this.barChartCounts = [];
    const statusCount: Record<string, number> = {};

    this.processedData.forEach(asset => {
      if (asset.status) {
        statusCount[asset.status] = (statusCount[asset.status] || 0) + 1;
      }
    });

    Object.entries(statusCount).forEach(([status, count]) => {
      this.barChartLabels.push(status);
      this.barChartCounts.push(count);
    });
    //TODO Set colors with variables
    this.barChartData = [
      {
        label: 'Quantity',
        data: this.barChartCounts,
        backgroundColor: [
          '#e07b75',
          '#cdb14f',
          '#20bf86',
          '#e075d5',
          '#496ef1',
        ],
      },
    ];
  }
}
