import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssetHistoryComponent } from '../components/asset-history.component';

const routes: Routes = [
  {
    path: '',
    component: AssetHistoryComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AssetHistoryRoutingModule {}
