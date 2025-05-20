import { ASSET_PATHS } from './assest/asset-paths.helper';
import { EMPLOYEE_PATHS } from './employee/employee-paths.helper';
import { REQUEST_PATHS } from './request/request-paths.helper';
import { USER_PATHS } from './user/user-paths.helper';
import { NOTIFICATION_PATHS } from './notification/notification-paths.helper';
import { ASSIGNMENT_PATHS } from './asignment/assigment-paths.helper';
import { WORKSPACE_PATHS } from './workspace/worspace-paths.helper';
import { TEAM_PATHS } from './team/team-paths.helper';
import { CATEGORY_PATHS } from './category/category-paths.helper';
import { USERS_PATHS } from './users/users-paths.helper';
import { ROLE_PATHS } from './role/role-paths.helper';

export const ASSET_BASE_PATH = '/asset-manager';
export const AUTH_BASE_PATH = '/auth-manager';
export const NOTIFICATION_BASE_PATH = '/notification-manager';

export const API_PATHS = {
  user: USER_PATHS,
  workspace: WORKSPACE_PATHS,
  request: REQUEST_PATHS,
  asset: ASSET_PATHS,
  team: TEAM_PATHS,
  employee: EMPLOYEE_PATHS,
  notifications: NOTIFICATION_PATHS,
  assignment: ASSIGNMENT_PATHS,
  category: CATEGORY_PATHS,
  users: USERS_PATHS,
  roles: ROLE_PATHS,
};
