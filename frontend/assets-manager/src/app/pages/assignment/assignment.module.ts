import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AssignmentRoutingModule } from './routes/assignment-routing.module';
import { AssignmentComponent } from './assignment.component';
import { ServerDataTableModule } from '../../modules/shared/server-data-table/server-data-table.module';
import { AssignmentFacade } from './facades/assignment.facade';
import { AssignmentRegistrationComponent } from './assignment-registration/assignment-registration.component';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputModule } from '../../modules/shared/input/input.module';
import { WorkspaceFacade } from '../workspace/facades/workspace.facade';
import { AssignmentDeleteComponent } from './assignment-delete/assignment-delete.component';
import { EmployeeFacade } from '../employee/facades/employee.facade';

@NgModule({
  declarations: [
    AssignmentComponent,
    AssignmentRegistrationComponent,
    AssignmentDeleteComponent,
  ],
  imports: [
    CommonModule,
    AssignmentRoutingModule,
    ServerDataTableModule,
    AutocompleteDropdownModule,
    FormsModule,
    InputModule,
    ReactiveFormsModule,
  ],
  providers: [AssignmentFacade, WorkspaceFacade, EmployeeFacade],
})
export class AssignmentModule {}
