export type AlignType = 'left' | 'center' | 'right';

export type SortDirectionType = 'asc' | 'desc';

export interface DataTableHeader {
  key: string;
  label: string;
  align?: AlignType;
  width?: string;
  sortable?: boolean;
}

export interface PaginationConfig {
  rowsPerPage?: number;
  rowsPerPageOptions?: number[];
}
