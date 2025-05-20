import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeamRoutingModule } from './routes/team-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TeamComponent } from './team.component';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { InputModule } from '../../modules/shared/input/input.module';
import { ServerDataTableModule } from '../../modules/shared/server-data-table/server-data-table.module';
import { TeamFacade } from './facades/team.facade';
import { CreateTeamComponent } from './components/create-team/create-team.component';
import { EmployeeFacade } from '../employee/facades/employee.facade';
import { UpdateTeamComponent } from './components/update-team/update-team.component';

@NgModule({
  declarations: [TeamComponent, CreateTeamComponent, UpdateTeamComponent],
  imports: [
    CommonModule,
    TeamRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    AutocompleteDropdownModule,
    InputModule,
    ServerDataTableModule,
  ],
  providers: [TeamFacade, EmployeeFacade],
})
export class TeamModule {}
