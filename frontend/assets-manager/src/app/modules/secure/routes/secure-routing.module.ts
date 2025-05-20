import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SECURE_ROUTES_CONFIG } from './secure-routes';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(SECURE_ROUTES_CONFIG)],
  exports: [RouterModule],
})
export class SecureRoutingModule {}
