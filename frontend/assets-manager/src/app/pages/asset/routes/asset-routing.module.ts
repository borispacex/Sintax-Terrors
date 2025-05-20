import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssetComponent } from '.././asset.component';

const routes: Routes = [
  {
    path: '',
    component: AssetComponent,
    data: { breadcrumb: 'Assets' },
  },
  {
    path: ':id/history',
    data: { breadcrumb: 'Asset History' },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AssetRoutingModule {}
