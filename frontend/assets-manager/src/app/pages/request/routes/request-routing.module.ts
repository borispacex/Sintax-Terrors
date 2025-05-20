import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RequestComponent } from '../request.component';

const routes: Routes = [
  { path: '', component: RequestComponent, data: { breadcrumb: 'Requests' } },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RequestRoutingModule {}
