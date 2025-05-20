import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AUTH_ROUTES_CONFIG } from './auth-routes';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(AUTH_ROUTES_CONFIG)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
