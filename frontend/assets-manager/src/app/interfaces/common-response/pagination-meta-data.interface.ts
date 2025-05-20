export interface PaginationMetaData {
  totalItems: number;
  currentPage: number;
  pageSize: number;
  totalPages: number;
}

export interface PaginationProps {
  page?: number;
  size?: number;
}

export interface RequestParams extends PaginationProps {
  [key: string]: any;
}
