import { Team } from '../../team/interfaces/team.interface';
import { BoliviaCity } from '../../../interfaces/enums/bolivia-city.enum';
import { EmployeeStatus } from '../../../interfaces/enums/employee-status.enum';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';

export interface Employee {
  id: number;
  ci: string;
  firstName: string;
  middleName?: string;
  lastName: string;
  secondLastName?: string;
  personalEmail?: string;
  workEmail: string;
  birthDate?: string;
  country: string;
  city: BoliviaCity;
  cellphoneNumber: string;
  status: EmployeeStatus;
  team?: Team;
  userId?: number;
  selectedImageID?: string;
  uploadedImageID?: string;
}

export interface EmployeeData {
  entities: Employee[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface EmployeeTable {
  name: string;
  ci: string;
  city: BoliviaCity;
  team: string;
  status: EmployeeStatus;
  employee: Employee;
}

export interface CreateEmployeeRequest {
  ci: string;
  firstName: string;
  middleName?: string;
  lastName: string;
  secondLastName?: string;
  personalEmail?: string;
  workEmail?: string;
  birthDate?: string;
  country: string;
  city: BoliviaCity;
  cellphoneNumber: string;
  status?: EmployeeStatus;
  userId?: number;
  teamId?: number;
  image?: File;
  selectedImageID?: string;
  uploadedImageID?: string;
}

export interface UpdateEmployeeRequest {
  id: number;
  ci: string;
  firstName: string;
  middleName?: string;
  lastName: string;
  secondLastName?: string;
  personalEmail?: string;
  workEmail?: string;
  birthDate?: string;
  country: string;
  city: BoliviaCity;
  cellphoneNumber?: string;
  status?: EmployeeStatus;
  userId?: number;
  teamId?: number;
  image?: File;
  selectedImageID?: string;
  uploadedImageID?: string;
}
