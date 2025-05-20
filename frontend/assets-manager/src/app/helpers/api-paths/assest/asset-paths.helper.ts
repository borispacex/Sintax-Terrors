import { ASSET_BASE_PATH } from '../api-paths.helper';
import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const ASSET_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `${ASSET_BASE_PATH}/asset/${params.id}`;
    },
  },
  delete: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/asset/${param?.id}`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `${ASSET_BASE_PATH}/asset${ParamsBuilder.build(param)}`;
    },
  },
  create: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/asset${ParamsBuilder.build(param)}`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/asset${ParamsBuilder.build(param)}`;
    },
  },
  getCurrentByEmployee: {
    generate: function (employee: string): string {
      return `${ASSET_BASE_PATH}/transaction/employees/${employee}/current-assets`;
    },
  },
  getAssetHistory: {
    generate: function (asset: string): string {
      return `${ASSET_BASE_PATH}/transaction/assets/${asset}/history`;
    },
  },
});
