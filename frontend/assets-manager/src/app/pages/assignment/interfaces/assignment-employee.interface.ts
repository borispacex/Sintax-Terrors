import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';

export interface AssignmentEmployee {
  id: number;
  ci: string;
  firstName: string;
  middleName: string;
  lastName: string;
  secondLastName: string;
  personalEmail: string;
  workEmail: string;
  birthDate: string;
  country: string;
  city: string;
  cellphoneNumber: string;
  status: string;
  team: number | null;
  selectedImageID: string;
  uploadedImageID: string;
}

export interface AssignmentData {
  entities: AssignmentEmployee[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface CreateAssignmentRequest {
  assetIds: number[];
  employeeId: number;
  reason: string;
  note: string;
}

export interface UpdateAssignmentRequest {
  id: number;
}
