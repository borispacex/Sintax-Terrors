import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestComponent } from './request.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RequestRoutingModule } from './routes/request-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { InputModule } from '../../modules/shared/input/input.module';
import { ProgressModule } from '../../modules/shared/progress/progress.module';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { CheckboxModule } from '../../modules/shared/checkbox/checkbox.module';
import { ServerDataTableModule } from '../../modules/shared/server-data-table/server-data-table.module';
import { DataTableModule } from '../../modules/shared/data-table/data-table.module';
import { RequestFacade } from './facades/request.facade';
import { CreateRequestComponent } from './components/create-team/create-request.component';
import { UpdateRequestComponent } from './components/update-team/update-request.component';

@NgModule({
  imports: [
    CommonModule,
    RequestRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    FontAwesomeModule,
    InputModule,
    AutocompleteDropdownModule,
    CheckboxModule,
    ProgressModule,
    DataTableModule,
    ServerDataTableModule,
  ],
  declarations: [
    RequestComponent,
    CreateRequestComponent,
    UpdateRequestComponent,
  ],
  providers: [RequestFacade],
})
export class RequestModule {}
