import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const ROLE_PATHS = Object.freeze({
  getByPage: {
    generate: function (param: ApiParams): string {
      return `/auth-manager/private/role${ParamsBuilder.build(param)}`;
    },
  },
});
