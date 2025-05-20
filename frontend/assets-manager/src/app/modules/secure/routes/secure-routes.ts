import { Routes } from '@angular/router';
import { SecureComponent } from '../secure.component';

export const SECURE_ROUTES_CONFIG: Routes = [
  {
    path: '',
    component: SecureComponent,
    data: { breadcrumb: 'Home' },
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard' },
      {
        path: 'dashboard',
        loadChildren: () =>
          import('../../../pages/dashboard/dashboard.module').then(
            m => m.DashboardModule
          ),
      },
      {
        path: 'assignment',
        loadChildren: () =>
          import('../../../pages/assignment/assignment.module').then(
            m => m.AssignmentModule
          ),
      },
      {
        path: 'employee',
        loadChildren: () =>
          import('../../../pages/employee/employee.module').then(
            m => m.EmployeeModule
          ),
      },
      {
        path: 'team',
        loadChildren: () =>
          import('../../../pages/team/team.module').then(
            module => module.TeamModule
          ),
      },
      {
        path: 'user',
        loadChildren: () =>
          import('../../../pages/user/user.module').then(
            module => module.UserModule
          ),
      },
      {
        path: 'workspace',
        loadChildren: () =>
          import('../../../pages/workspace/workspace.module').then(
            module => module.WorkspaceModule
          ),
      },
      {
        path: 'request',
        loadChildren: () =>
          import('../../../pages/request/request.module').then(
            module => module.RequestModule
          ),
      },
      {
        path: 'asset',
        loadChildren: () =>
          import('../../../pages/asset/asset.module').then(
            module => module.AssetModule
          ),
      },
      {
        path: 'history/:id',
        loadChildren: () =>
          import('../../../pages/history/asset-history.module').then(
            module => module.AssetHistoryModule
          ),
      },
      { path: '**', redirectTo: 'dashboard', pathMatch: 'full' },
    ],
  },
];
