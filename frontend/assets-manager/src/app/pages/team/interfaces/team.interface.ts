import { Employee } from '../../employee/interfaces/employee.interface';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';

export interface Team {
  id: number;
  name: string;
  description?: string;
  isActive: boolean;
  projectManager?: Employee;
}

export interface TeamTable {
  name: string;
  description?: string;
  isActive: string;
  manager?: string;
  team: Team;
}

export interface TeamData {
  entities: Team[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface CreateTeamRequest {
  name: string;
  description?: string;
  isActive: boolean;
  projectManagerId?: number;
}

export interface UpdateTeamRequest {
  id: number;
  name: string;
  description?: string;
  isActive: boolean;
  projectManagerId?: number;
}
