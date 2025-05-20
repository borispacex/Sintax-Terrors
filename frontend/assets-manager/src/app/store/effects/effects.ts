import { LoadWorkspacesEffect } from './load/load-workspaces.effect';
import { UpdateWorkspacesEffect } from './update/update-workspaces.effect';
import { DeleteWorkspacesEffect } from './delete/delete-workspaces.effect';
import { CreateWorkspacesEffect } from './create/create-workspaces.effect';
import { LoadRequestsEffect } from './load/load-requests.effect';
import { UpdateRequestsEffect } from './update/update-requests.effect';
import { DeleteRequestsEffect } from './delete/delete-requests.effect';
import { CreateRequestsEffect } from './create/create-requests.effect';
import { LoadAssetsEffect } from './load/load-assets.effect';
import { UpdateAssetsEffect } from './update/update-assets.effect';
import { DeleteAssetsEffect } from './delete/delete-assets.effect';
import { CreateAssetsEffect } from './create/create-assets.effect';
import { LoadTeamsEffect } from './load/load-teams.effect';
import { DeleteTeamEffect } from './delete/delete-team.effect';
import { CreateTeamEffect } from './create/create-team.effect';
import { UpdateTeamEffect } from './update/update-team.effect';
import { LoadEmployeesEffect } from './load/load-employees.effect';
import { DeleteEmployeeEffect } from './delete/delete-employee.effect';
import { CreateEmployeeEffect } from './create/create-employee.effect';
import { UpdateEmployeeEffect } from './update/update-employee.effect';
import { LoadNotificationsEffect } from './load/load-notifications.effect';
import { UpdateNotificationsEffect } from './update/update-notifications.effect';
import { LoadUserEffect } from './load/load-user.effect';
import { LoadAssignmentsEffect } from './load/load-assignments.effect';
import { UpdateAssignmentsEffect } from './update/update-assignments.effect';
import { DeleteAssignmentsEffect } from './delete/delete-assignments.effect';
import { CreateAssignmentsEffect } from './create/create-assignments.effect';
import { LoadCategoriesEffect } from './load/load-categories.effect';
import { LoadUsersEffect } from './load/load-users.effect';
import { UpdateUsersEffect } from './update/update-users.effect';
import { CreateUsersEffect } from './create/create-users.effect';

export const ENTITY_EFFECTS = [
  LoadWorkspacesEffect,
  UpdateWorkspacesEffect,
  DeleteWorkspacesEffect,
  CreateWorkspacesEffect,

  LoadRequestsEffect,
  UpdateRequestsEffect,
  DeleteRequestsEffect,
  CreateRequestsEffect,
  LoadAssetsEffect,
  UpdateAssetsEffect,
  DeleteAssetsEffect,
  CreateAssetsEffect,

  LoadTeamsEffect,
  UpdateTeamEffect,
  DeleteTeamEffect,
  CreateTeamEffect,

  LoadEmployeesEffect,
  UpdateEmployeeEffect,
  DeleteEmployeeEffect,
  CreateEmployeeEffect,

  LoadUserEffect,

  LoadAssignmentsEffect,
  UpdateAssignmentsEffect,
  DeleteAssignmentsEffect,
  CreateAssignmentsEffect,

  LoadNotificationsEffect,
  UpdateNotificationsEffect,

  LoadCategoriesEffect,

  LoadUsersEffect,
  UpdateUsersEffect,
  CreateUsersEffect,
];
