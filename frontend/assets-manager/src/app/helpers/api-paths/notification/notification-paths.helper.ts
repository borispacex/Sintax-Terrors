import { NOTIFICATION_BASE_PATH } from '../api-paths.helper';
import { ApiParams, ParamsBuilder } from '../api-params.builder';

export const NOTIFICATION_PATHS = Object.freeze({
  getById: {
    generate: function (params: ApiParams): string {
      return `${NOTIFICATION_BASE_PATH}/notification/${params.id}`;
    },
  },
  getByPage: {
    generate: function (param: ApiParams): string {
      return `${NOTIFICATION_BASE_PATH}/notification${ParamsBuilder.build(param)}`;
    },
  },
  update: {
    generate: function (param?: ApiParams): string {
      return `${NOTIFICATION_BASE_PATH}/notification${ParamsBuilder.build(param)}`;
    },
  },
});
