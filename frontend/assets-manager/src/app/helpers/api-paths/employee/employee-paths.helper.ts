import { ASSET_BASE_PATH } from '../api-paths.helper';
import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const EMPLOYEE_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `${ASSET_BASE_PATH}/employee/${params.id}`;
    },
  },
  delete: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/employee/${param?.id}`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `${ASSET_BASE_PATH}/employee${ParamsBuilder.build(param)}`;
    },
  },
  create: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/employee${ParamsBuilder.build(param)}`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/employee${ParamsBuilder.build(param)}`;
    },
  },
});
