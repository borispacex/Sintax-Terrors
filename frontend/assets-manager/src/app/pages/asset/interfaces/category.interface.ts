export interface Category {
  id: number;
  name: string;
  description: string;
  usefulLifeMonths: number;
  isDepreciable: boolean;
}

export interface UpdateCategoryRequest {
  id: number;
  name: string;
  description: string;
  usefulLifeMonths: number;
  isDepreciable: boolean;
}

export interface CreateCategoryRequest {
  name: string;
  description: string;
  usefulLifeMonths: number;
  isDepreciable: boolean;
}
