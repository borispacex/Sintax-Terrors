import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AssetComponent } from '././asset.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AssetRoutingModule } from './routes/asset-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AssetFormComponent } from './asset-form/asset-form.component';
import { InputModule } from '../../modules/shared/input/input.module';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { ProgressModule } from '../../modules/shared/progress/progress.module';
import { DataTableModule } from '../../modules/shared/data-table/data-table.module';
import { ServerDataTableModule } from '../../modules/shared/server-data-table/server-data-table.module';
import { AssetFacade } from './facades/asset.facade';
import { CategoryFacade } from './facades/category.facade';
import { WorkspaceFacade } from '../workspace/facades/workspace.facade';

@NgModule({
  imports: [
    CommonModule,
    AssetRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    FontAwesomeModule,
    InputModule,
    AutocompleteDropdownModule,
    ProgressModule,
    DataTableModule,
    ServerDataTableModule,
  ],
  declarations: [AssetComponent, AssetFormComponent],
  providers: [AssetFacade, CategoryFacade, WorkspaceFacade],
})
export class AssetModule {}
