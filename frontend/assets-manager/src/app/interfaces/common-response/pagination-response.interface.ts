import { PaginationMetaData } from './pagination-meta-data.interface';

export interface PaginationResponse<T> extends PaginationMetaData {
  items: T[];
}
