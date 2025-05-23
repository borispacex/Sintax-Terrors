import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssignmentComponent } from '../assignment.component';

const routes: Routes = [
  {
    path: '',
    component: AssignmentComponent,
    data: { breadcrumb: 'Assignment' },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AssignmentRoutingModule {}
