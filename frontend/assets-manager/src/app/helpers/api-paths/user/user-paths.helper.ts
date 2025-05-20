import { AUTH_BASE_PATH } from '../api-paths.helper';

export const USER_PATHS = Object.freeze({
  getByToken: {
    generate: function (): string {
      return `${AUTH_BASE_PATH}/private/users/me`;
    },
  },
});
