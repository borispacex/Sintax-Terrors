import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const USERS_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `/auth-manager/private/users/${params.id}`;
    },
  },
  delete: {
    generate: function (param?: ApiParams): string {
      return `/auth-manager/private/users/${param?.id}`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `/auth-manager/private/users${ParamsBuilder.build(param)}`;
    },
  },
  create: {
    generate: function (param?: ApiParams): string {
      return `/auth-manager/private/users${ParamsBuilder.build(param)}`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `/auth-manager/private/users${ParamsBuilder.build(param)}`;
    },
  },
});
