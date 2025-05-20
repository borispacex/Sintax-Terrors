import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkspaceRoutingModule } from './routes/workspace-routing.module';
import { WorkspaceComponent } from './workspace.component';
import { DataTableModule } from '../../modules/shared/data-table/data-table.module';
import { ModalModule } from '../../modules/shared/modal/modal.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputModule } from '../../modules/shared/input/input.module';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { ProgressModule } from '../../modules/shared/progress/progress.module';
import { ServerDataTableModule } from '../../modules/shared/server-data-table/server-data-table.module';
import { WorkspaceRegistrationComponent } from './workspace-registration/workspace-registration.component';
import { WorkspaceFacade } from './facades/workspace.facade';
@NgModule({
  declarations: [WorkspaceComponent, WorkspaceRegistrationComponent],
  imports: [
    CommonModule,
    WorkspaceRoutingModule,
    DataTableModule,
    ModalModule,
    FormsModule,
    InputModule,
    ReactiveFormsModule,
    AutocompleteDropdownModule,
    ProgressModule,
    ServerDataTableModule,
  ],
  providers: [WorkspaceFacade],
})
export class WorkspaceModule {}
