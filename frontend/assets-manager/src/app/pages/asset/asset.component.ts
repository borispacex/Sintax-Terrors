import {
  ChangeDetectionStrategy,
  Component,
  OnInit,
  TemplateRef,
} from '@angular/core';
import { ServerDataTableHeader } from '../../modules/shared/server-data-table/interfaces/server-data-table.interface';
import { Asset } from './interfaces/asset.interface';
import { ModalService } from '../../modules/shared/modal/services/modal.service';
import { PaginationResponse } from '../../interfaces/common-response/pagination-response.interface';
import { AssetFacade } from './facades/asset.facade';
import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';
import { Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { assetTableHeaders } from './helpers/asset-table.config';
import {
  initialActiveAsset,
  initialAssetTableData,
} from './helpers/initial-asset-variables.helper';

@Component({
  selector: 'am-asset',
  templateUrl: './asset.component.html',
  styleUrls: ['./asset.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssetComponent implements OnInit {
  public tableHeaders: ServerDataTableHeader[] = assetTableHeaders;
  public tableData$: Observable<PaginationResponse<Asset>> = of(
    initialAssetTableData
  );
  public isLoading: boolean = false;
  public activeAsset: Asset = initialActiveAsset;

  private _params: RequestParams = {
    page: 1,
    size: 10,
  };

  constructor(
    private _assetFacade: AssetFacade,
    private _modalService: ModalService
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  private _initialize(): void {
    this._assetFacade.loadAssets(this._params);

    this.tableData$ = this._assetFacade.getAssetsData().pipe(
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

  public onPageChange(page: number): void {
    this._assetFacade.loadAssets({ ...this._params, page });
  }

  public onSortChange(event: {
    field: string;
    direction: 'asc' | 'desc';
  }): void {
    this._assetFacade.loadAssets({
      ...this._params,
      direction: event.direction.toUpperCase(),
      order: event.field.toUpperCase(),
    });
  }

  public onRowsPerPageChange(size: number): void {
    this._assetFacade.loadAssets({ ...this._params, size });
  }

  public openModal(template: TemplateRef<any>): void {
    this._modalService.open(template, {
      tittle: 'New Asset',
      size: 'md',
      scrollable: true,
    });
  }

  public onUpdateItem(item: Asset, updateModal: TemplateRef<any>): void {
    this.activeAsset = { ...item };
    this._modalService.open(updateModal, {
      size: 'md',
      scrollable: true,
      tittle: 'Update Asset',
    });
  }

  public onRemoveItem(item: Asset): void {
    this._assetFacade.deleteAsset(item.id);
  }
}
