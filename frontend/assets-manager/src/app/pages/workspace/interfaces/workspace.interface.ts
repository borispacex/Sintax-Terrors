import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';

export interface Workspace {
  id: number;
  location: string;
  city: string;
}

export interface WorkspaceData {
  entities: Workspace[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface UpdateWorkspaceRequest {
  id: number;
  location: string;
  city: string;
}

export interface CreateWorkspaceRequest {
  location: string;
  city: string;
}
