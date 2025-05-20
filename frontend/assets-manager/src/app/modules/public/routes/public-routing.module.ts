import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PUBLIC_ROUTES_CONFIG } from './public-routes';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(PUBLIC_ROUTES_CONFIG)],
  exports: [RouterModule],
})
export class PublicRoutingModule {}
