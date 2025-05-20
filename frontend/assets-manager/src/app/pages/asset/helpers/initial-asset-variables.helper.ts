import { Asset } from '../interfaces/asset.interface';

export const initialAssetTableData = {
  items: [],
  totalItems: 0,
  currentPage: 1,
  pageSize: 10,
  totalPages: 1,
};

export const initialActiveAsset: Asset = {
  id: 0,
  serialNumber: '',
  model: '',
  manufacturer: '',
  categoryId: 0,
  categoryName: '',
  workspaceId: 0,
  location: '',
  purchaseDate: '',
  purchaseCost: 0,
  status: '',
  warrantyExpiration: '',
  notes: '',
  ageInMonths: 0,
  underWarranty: false,
};
