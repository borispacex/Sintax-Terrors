import { ASSET_BASE_PATH } from '../api-paths.helper';
import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const ASSIGNMENT_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `${ASSET_BASE_PATH}/assignment/${params.id}`;
    },
  },
  delete: {
    generate: function (): string {
      return `${ASSET_BASE_PATH}/transaction/returns`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `${ASSET_BASE_PATH}/employee/withTransaction${ParamsBuilder.build(param)}`;
    },
  },
  create: {
    generate: function (): string {
      return `${ASSET_BASE_PATH}/transaction/assignments`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `${ASSET_BASE_PATH}/assignment${ParamsBuilder.build(param)}`;
    },
  },
});
