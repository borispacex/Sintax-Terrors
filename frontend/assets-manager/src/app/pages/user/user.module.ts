import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './routes/user-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserComponent } from './user.component';
import { ServerDataTableModule } from '../../modules/shared/server-data-table/server-data-table.module';
import { UsersFacade } from './facades/users.facade';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { InputModule } from '../../modules/shared/input/input.module';
import { AssignmentFacade } from '../assignment/facades/assignment.facade';
import { AmUiInputModule } from '@samuelbaz/am-ui';

@NgModule({
  declarations: [UserComponent, CreateUserComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ServerDataTableModule,
    AutocompleteDropdownModule,
    InputModule,
    AmUiInputModule,
  ],
  providers: [AssignmentFacade, UsersFacade],
})
export class UserModule {}
