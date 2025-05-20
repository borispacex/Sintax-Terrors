import {
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
} from '@angular/core';
import { ServerDataTableHeader } from './interfaces/server-data-table.interface';
import { Observable } from 'rxjs';
import {
  ScreenSize,
  ScreenSizeService,
} from '../../../services/screen-size.service';
import { PaginationResponse } from '../../../interfaces/common-response/pagination-response.interface';

@Component({
  selector: 'am-server-data-table',
  templateUrl: './server-data-table.component.html',
  styleUrls: ['./server-data-table.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ServerDataTableComponent<T extends Record<string, any>>
  implements OnChanges
{
  @Input() public headers: ServerDataTableHeader[] = [];
  @Input() public data!: PaginationResponse<T>;
  @Input() public loading: boolean = false;
  @Input() public showActions: boolean = false;
  @Input() public showUpdateAction: boolean = false;
  @Input() public showDeleteAction: boolean = false;

  @Output() public pageChange = new EventEmitter<number>();
  @Output() public sortChange = new EventEmitter<{
    field: string;
    direction: 'asc' | 'desc';
  }>();
  @Output() public rowsPerPageChange = new EventEmitter<number>();
  @Output() public onRemoveItem = new EventEmitter<T>();
  @Output() public onUpdateItem = new EventEmitter<T>();

  public currentSortField: string = 'id';
  public currentSortDirection: 'asc' | 'desc' = 'asc';

  public screenSize$: Observable<ScreenSize> =
    this._screenSizeService.screenSize$;

  constructor(private _screenSizeService: ScreenSizeService) {}

  public ngOnChanges(changes: SimpleChanges): void {
    if (changes['data'] && this.data?.items) {
      if (this.data.currentPage > this.data.totalPages) {
        this.pageChange.emit(1);
      }
    }
  }

  public onSort(field: string): void {
    if (this.currentSortField === field) {
      this.currentSortDirection =
        this.currentSortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.currentSortField = field;
      this.currentSortDirection = 'asc';
    }
    this.sortChange.emit({
      field: this.currentSortField,
      direction: this.currentSortDirection,
    });
  }

  public getPageNumbers(): number[] {
    const pages: number[] = [];
    const maxVisiblePages = 5;

    if (this.data.totalPages <= maxVisiblePages) {
      for (let i = 1; i <= this.data.totalPages; i++) {
        pages.push(i);
      }
    } else {
      let start = Math.max(1, this.data.currentPage - 2);
      const end = Math.min(this.data.totalPages, start + maxVisiblePages - 1);

      if (end - start < maxVisiblePages - 1) {
        start = Math.max(1, end - maxVisiblePages + 1);
      }

      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
    }

    return pages;
  }

  public onPageChange(page: number): void {
    if (page >= 1 && page <= this.data.totalPages) {
      this.pageChange.emit(page);
    }
  }

  public onRowsPerPageChange(event: Event): void {
    const select = event.target as HTMLSelectElement;
    this.rowsPerPageChange.emit(parseInt(select.value));
  }

  public getCellClasses(header: ServerDataTableHeader): string {
    return [
      `am-data-table__cell`,
      `am-data-table__cell--${header.align || 'left'}`,
      header.width ? 'am-data-table__cell--fixed-width' : '',
    ].join(' ');
  }

  public removeItem(row: T): void {
    this.onRemoveItem.emit(row);
  }

  public updateItem(row: T): void {
    this.onUpdateItem.emit(row);
  }
}
