import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const REQUEST_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `/asset-manager/request/${params.id}`;
    },
  },
  delete: {
    generate: function (param?: ApiParams): string {
      return `/asset-manager/request/${param?.id}`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `/asset-manager/request${ParamsBuilder.build(param)}`;
    },
  },
  create: {
    generate: function (param?: ApiParams): string {
      return `/asset-manager/request${ParamsBuilder.build(param)}`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `/asset-manager/request${ParamsBuilder.build(param)}`;
    },
  },
});
