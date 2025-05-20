import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeRoutingModule } from './routes/employee-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeComponent } from './employee.component';
import { ServerDataTableModule } from '../../modules/shared/server-data-table/server-data-table.module';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { EmployeeFacade } from './facades/employee.facade';
import { TeamFacade } from '../team/facades/team.facade';
import { InputModule } from '../../modules/shared/input/input.module';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';

@NgModule({
  declarations: [
    EmployeeComponent,
    CreateEmployeeComponent,
    UpdateEmployeeComponent,
  ],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ServerDataTableModule,
    InputModule,
    AutocompleteDropdownModule,
  ],
  providers: [EmployeeFacade, TeamFacade],
})
export class EmployeeModule {}
