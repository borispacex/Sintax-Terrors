import { ASSET_BASE_PATH } from '../api-paths.helper';
import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const CATEGORY_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `${ASSET_BASE_PATH}/category/${params.id}`;
    },
  },
  delete: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/category/${param?.id}`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `${ASSET_BASE_PATH}/category${ParamsBuilder.build(param)}`;
    },
  },
  create: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/category${ParamsBuilder.build(param)}`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/category${ParamsBuilder.build(param)}`;
    },
  },
});
