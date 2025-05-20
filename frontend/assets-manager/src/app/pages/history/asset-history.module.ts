import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AssetHistoryComponent } from './components/asset-history.component';
import { AssetHistoryRoutingModule } from './routes/asset-history-routing.module';
import { AssetFacade } from '../asset/facades/asset.facade';
import { AssignmentFacade } from '../assignment/facades/assignment.facade';

@NgModule({
  imports: [CommonModule, AssetHistoryRoutingModule],
  declarations: [AssetHistoryComponent],
  exports: [],
  providers: [AssetFacade, AssignmentFacade],
})
export class AssetHistoryModule {}
