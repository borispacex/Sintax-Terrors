import { WorkspaceState } from './entities/workspace-state';
import { RequestState } from './entities/request-state';
import { AssetState } from './entities/asset-state';
import { TeamState } from './entities/team-state';
import { EmployeeState } from './entities/employee-state';
import { AssignmentState } from './entities/asignment-state';
import { NotificationState } from './entities/notification-state';
import { CategoryState } from './entities/category-state';
import { UsersState } from './entities/user-state';

export interface CollectionState {
  workspaces: WorkspaceState;
  requests: RequestState;
  assets: AssetState;
  teams: TeamState;
  employees: EmployeeState;
  assignments: AssignmentState;
  notifications: NotificationState;
  categories: CategoryState;
  users: UsersState;
}
