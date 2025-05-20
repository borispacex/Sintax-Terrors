export interface CommonResponse<T> {
  code: number;
  message: string;
  content: T;
}
