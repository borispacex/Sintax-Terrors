import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const WORKSPACE_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `/asset-manager/workspace/${params.id}`;
    },
  },
  delete: {
    generate: function (param?: ApiParams): string {
      return `/asset-manager/workspace/${param?.id}`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `/asset-manager/workspace${ParamsBuilder.build(param)}`;
    },
  },
  create: {
    generate: function (param?: ApiParams): string {
      return `/asset-manager/workspace${ParamsBuilder.build(param)}`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `/asset-manager/workspace${ParamsBuilder.build(param)}`;
    },
  },
});
