import {
  Component,
  Input,
  Output,
  EventEmitter,
  ChangeDetectionStrategy,
} from '@angular/core';
import {
  DataTableHeader,
  PaginationConfig,
  SortDirectionType,
} from './interfaces/data-table.interface';
import {
  ScreenSize,
  ScreenSizeService,
} from '../../../services/screen-size.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'am-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DataTableComponent {
  @Input() headers: DataTableHeader[] = [];
  @Input() data: any[] = []; //TODO Type the variable as a generic type
  @Input() pagination: PaginationConfig = {
    rowsPerPage: 10,
    rowsPerPageOptions: [5, 10, 25, 50],
  };
  @Input() sortable: boolean = true;

  @Output() rowClicked = new EventEmitter<any>();
  @Output() sortChanged = new EventEmitter<{
    field: string;
    direction: 'asc' | 'desc';
  }>();
  @Input() searchEnabled: boolean = true;
  @Input() searchPlaceholder: string = 'Search...';
  searchTerm: string = '';

  public currentPage = 1;
  public sortField = '';
  public sortDirection: SortDirectionType = 'asc';

  public screenSize$: Observable<ScreenSize> =
    this._screenSizeService.screenSize$;

  constructor(private _screenSizeService: ScreenSizeService) {}

  getProperty(obj: any, key: string): any {
    return obj[key];
  }

  get computedData(): any[] {
    return this.data.map(row => {
      const processedRow: any = {};
      this.headers.forEach(header => {
        processedRow[header.key] = row[header.key];
      });
      return processedRow;
    });
  }

  get totalPages(): number {
    const total = Math.ceil(
      this.filteredData.length / (this.pagination.rowsPerPage || 10)
    );
    return total;
  }

  get paginatedData(): any[] {
    const sortedData = [...this.filteredData];

    if (this.sortField && this.sortable) {
      sortedData.sort((a, b) => {
        const valA = a[this.sortField];
        const valB = b[this.sortField];
        if (valA < valB) {
          return this.sortDirection === 'asc' ? -1 : 1;
        }
        if (valA > valB) {
          return this.sortDirection === 'asc' ? 1 : -1;
        }
        return 0;
      });
    }

    const rowsPerPage = this.pagination.rowsPerPage || 10;
    const start = (this.currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    return sortedData.slice(start, end);
  }

  public onSearchChange(term: string): void {
    this.searchTerm = term;
    this.currentPage = 1;
    if (this.currentPage > this.totalPages && this.totalPages > 0) {
      this.currentPage = this.totalPages;
    }
  }

  get filteredData(): any[] {
    let filteredData = [...this.data];

    if (this.searchTerm && this.searchEnabled) {
      const term = this.searchTerm.toLowerCase();
      filteredData = filteredData.filter(row =>
        this.headers.some(header => {
          const value = this.getProperty(row, header.key);
          return value?.toString().toLowerCase().includes(term);
        })
      );
    }

    return filteredData;
  }

  public sort(field: string): void {
    if (!this.sortable) {
      return;
    }

    if (this.sortField === field) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortField = field;
      this.sortDirection = 'asc';
    }

    this.sortChanged.emit({
      field: this.sortField,
      direction: this.sortDirection,
    });
  }

  public changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }

  public changeRowsPerPage(rows: number | string | undefined): void {
    const parsed = Number(rows);
    this.pagination.rowsPerPage = isNaN(parsed) ? 10 : parsed;
    this.currentPage = 1;
  }

  public onRowClick(row: any): void {
    this.rowClicked.emit(row);
  }

  public getPageNumbers(): number[] {
    const pages: number[] = [];
    const maxVisiblePages = 5;

    if (this.totalPages <= maxVisiblePages) {
      for (let i = 1; i <= this.totalPages; i++) {
        pages.push(i);
      }
    } else {
      let start = Math.max(1, this.currentPage - 2);
      const end = Math.min(this.totalPages, start + maxVisiblePages - 1);

      if (end - start < maxVisiblePages - 1) {
        start = Math.max(1, end - maxVisiblePages + 1);
      }

      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
    }

    return pages;
  }

  get pageRange(): { start: number; end: number } {
    const rowsPerPage = this.pagination?.rowsPerPage ?? 10;
    const start = (this.currentPage - 1) * rowsPerPage + 1;
    const end = Math.min(
      this.currentPage * rowsPerPage,
      this.filteredData.length
    );
    return { start, end };
  }
}
