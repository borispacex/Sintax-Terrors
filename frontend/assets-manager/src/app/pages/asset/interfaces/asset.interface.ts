import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';

export interface Asset {
  id: number;
  serialNumber: string;
  model: string;
  manufacturer: string;
  categoryId: number;
  categoryName: string;
  workspaceId: number;
  location: string;
  purchaseDate: string;
  purchaseCost: number;
  status: string;
  warrantyExpiration: string;
  notes: string;
  ageInMonths: number;
  underWarranty: boolean;
  workspaceLocation?: string;
}

export interface AssetData {
  entities: Asset[];
  pagination: PaginationMetaData;
  isLoading: boolean;
  params: RequestParams;
}

export interface UpdateAssetRequest {
  id: number;
  categoryId: number;
  serialNumber: string;
  status: string;
  model: string;
  manufacturer: string;
  workspaceId: number;
  purchaseDate: string;
  purchaseCost: number;
  warrantyExpiration: string;
  city: string;
  notes: string;
}

export interface CreateAssetRequest {
  categoryId: number;
  serialNumber: string;
  status: string;
  model: string;
  manufacturer: string;
  workspaceId: number;
  purchaseDate: string;
  purchaseCost: number;
  warrantyExpiration: string;
  city: string;
  notes: string;
}
