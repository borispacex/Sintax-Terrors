import { PaginationResponse } from './pagination-response.interface';

export interface CommonPaginationResponse<T> {
  code: number;
  message: string;
  content: PaginationResponse<T>;
}
