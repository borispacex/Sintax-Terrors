import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
import { Employee } from '../../employee/interfaces/employee.interface';
import { RequestStatus } from '../enums/status.enum';
import { RequestType } from '../enums/request-type.enum';

export interface Request {
  id: number;
  title: string;
  description?: string;
  status: RequestStatus;
  type: RequestType;
  createdAt?: string;
  employee: Employee;
}

export interface RequestTable {
  employeeName: string;
  title: string;
  type: RequestType;
  status: RequestStatus;
  request: Request;
}

export interface RequestData {
  entities: Request[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface UpdateRequestRequest {
  id: number;
  title: string;
  description?: string;
  status: RequestStatus;
  type: RequestType;
  assetIds?: string;
  employeeId: number;
}

export interface CreateRequestRequest {
  title: string;
  description?: string;
  status: RequestStatus;
  type: RequestType;
  assetIds?: string;
  employeeId: number;
}
