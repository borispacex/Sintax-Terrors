import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WorkspaceComponent } from '../workspace.component';

const routes: Routes = [
  {
    path: '',
    component: WorkspaceComponent,
    data: { breadcrumb: 'Workspace' },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class WorkspaceRoutingModule {}
