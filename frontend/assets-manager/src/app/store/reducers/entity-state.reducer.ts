import { createReducer, on } from '@ngrx/store';
import { CollectionState } from '../states/collection-state';
import {
  initialWorkspaceState,
  workspaceEntityAdapter,
} from '../states/entities/workspace-state';
import {
  initialCategoryState,
  categoryEntityAdapter,
} from '../states/entities/category-state';
import * as WorkspaceAction from '../actions/entities/workspace.action';
import * as RequestAction from '../actions/entities/request.action';
import * as AssetAction from '../actions/entities/asset.action';
import * as CategoryAction from '../actions/entities/category.action';
import * as UsersAction from '../actions/entities/users.action';

import {
  initialRequestState,
  requestEntityAdapter,
} from '../states/entities/request-state';
import {
  initialAssetState,
  assetEntityAdapter,
} from '../states/entities/asset-state';
import * as TeamAction from '../actions/entities/team.action';
import * as AssignmentAction from '../actions/entities/assignment.action';
import * as EmployeeActions from '../actions/entities/employee.action';
import {
  initialTeamState,
  teamEntityAdapter,
} from '../states/entities/team-state';
import {
  employeeEntityAdapter,
  initialEmployeeState,
} from '../states/entities/employee-state';
import * as NotificationAction from '../actions/entities/notification.action';
import {
  assignmentEntityAdapter,
  initialAssignmentState,
} from '../states/entities/asignment-state';
import {
  initialNotificationState,
  notificationEntityAdapter,
} from '../states/entities/notification-state';
import {
  initialUsersState,
  userEntityAdapter,
} from '../states/entities/user-state';

export const initialState: CollectionState = {
  workspaces: initialWorkspaceState,
  teams: initialTeamState,
  employees: initialEmployeeState,
  assignments: initialAssignmentState,
  requests: initialRequestState,
  assets: initialAssetState,
  notifications: initialNotificationState,
  categories: initialCategoryState,
  users: initialUsersState,
};

export const entityStateReducer = createReducer(
  initialState,

  // Workspace reducers
  on(WorkspaceAction.loadWorkspaces, (state, action) => ({
    ...state,
    workspaces: {
      ...state.workspaces,
      loading: true,
      params: action.params,
    },
  })),
  on(WorkspaceAction.loadWorkspacesSuccess, (state, action) => ({
    ...state,
    workspaces: workspaceEntityAdapter.setAll(action.workspaces, {
      ...state.workspaces,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(WorkspaceAction.updateWorkspaceSuccess, (state, action) => ({
    ...state,
    workspaces: workspaceEntityAdapter.upsertOne(action.workspace, {
      ...state.workspaces,
    }),
  })),
  // Employee reducers
  on(EmployeeActions.loadEmployees, (state, action) => ({
    ...state,
    employees: {
      ...state.employees,
      loading: true,
      params: action.params,
    },
  })),
  on(EmployeeActions.loadEmployeesSuccess, (state, action) => ({
    ...state,
    employees: employeeEntityAdapter.setAll(action.employees, {
      ...state.employees,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(EmployeeActions.updateEmployeeSuccess, (state, action) => ({
    ...state,
    employees: employeeEntityAdapter.upsertOne(action.employee, {
      ...state.employees,
    }),
  })),

  // Team reducers
  on(TeamAction.loadTeams, (state, action) => ({
    ...state,
    teams: {
      ...state.teams,
      loading: true,
      params: action.params,
    },
  })),
  on(RequestAction.loadRequestsSuccess, (state, action) => ({
    ...state,
    requests: requestEntityAdapter.setAll(action.requests, {
      ...state.requests,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(RequestAction.updateRequestSuccess, (state, action) => ({
    ...state,
    requests: requestEntityAdapter.upsertOne(action.request, {
      ...state.requests,
    }),
  })),
  on(AssetAction.loadAssetsSuccess, (state, action) => ({
    ...state,
    assets: assetEntityAdapter.setAll(action.assets, {
      ...state.assets,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(AssetAction.updateAssetSuccess, (state, action) => ({
    ...state,
    assets: assetEntityAdapter.upsertOne(action.asset, {
      ...state.assets,
    }),
  })),
  on(TeamAction.loadTeamsSuccess, (state, action) => ({
    ...state,
    teams: teamEntityAdapter.setAll(action.teams, {
      ...state.teams,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(TeamAction.updateTeamSuccess, (state, action) => ({
    ...state,
    teams: teamEntityAdapter.upsertOne(action.team, {
      ...state.teams,
    }),
  })),
  on(NotificationAction.loadNotificationsSuccess, (state, action) => ({
    ...state,
    notifications: notificationEntityAdapter.setAll(action.notifications, {
      ...state.notifications,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(NotificationAction.updateNotificationSuccess, (state, action) => ({
    ...state,
    notifications: notificationEntityAdapter.upsertOne(action.notification, {
      ...state.notifications,
    }),
  })),

  //Notification reducer
  on(NotificationAction.loadNotificationsSuccess, (state, action) => ({
    ...state,
    notifications: notificationEntityAdapter.setAll(action.notifications, {
      ...state.notifications,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(NotificationAction.updateNotificationSuccess, (state, action) => ({
    ...state,
    notifications: notificationEntityAdapter.upsertOne(action.notification, {
      ...state.notifications,
    }),
  })),

  //Assignment reducer
  on(AssignmentAction.loadAssignmentsSuccess, (state, action) => ({
    ...state,
    assignments: assignmentEntityAdapter.setAll(action.assignments, {
      ...state.assignments,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(AssignmentAction.updateAssignmentSuccess, (state, action) => ({
    ...state,
    assignments: assignmentEntityAdapter.upsertOne(action.assignment, {
      ...state.assignments,
    }),
  })),
  on(AssignmentAction.updateParams, (state, action) => ({
    ...state,
    assignments: {
      ...state.assignments,
      params: {
        ...state.assignments.params,
        ...action.params,
      },
    },
  })),
  // Category reducers
  on(CategoryAction.loadCategories, (state, action) => ({
    ...state,
    categories: {
      ...state.categories,
      loading: true,
      params: action.params,
    },
  })),
  on(CategoryAction.loadCategoriesSuccess, (state, action) => ({
    ...state,
    categories: categoryEntityAdapter.setAll(action.categories, {
      ...state.categories,
      pagination: action.pagination,
      loading: false,
    }),
  })),

  // Users reducers
  on(UsersAction.loadUsers, (state, action) => ({
    ...state,
    users: {
      ...state.users,
      loading: true,
      params: action.params,
    },
  })),
  on(UsersAction.loadUsersSuccess, (state, action) => ({
    ...state,
    users: userEntityAdapter.setAll(action.users, {
      ...state.users,
      pagination: action.pagination,
      loading: false,
    }),
  })),
  on(UsersAction.updateUserSuccess, (state, action) => ({
    ...state,
    users: userEntityAdapter.upsertOne(action.user, {
      ...state.users,
    }),
  }))
);
