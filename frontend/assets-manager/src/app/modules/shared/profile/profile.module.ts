import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserFacade } from './facades/user.facade';
import { ProfileComponent } from './profile.component';

@NgModule({
  declarations: [ProfileComponent],
  exports: [ProfileComponent],
  imports: [CommonModule],
  providers: [UserFacade],
})
export class ProfileModule {}
