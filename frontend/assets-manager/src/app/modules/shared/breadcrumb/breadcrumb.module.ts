import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BreadcrumbComponent } from './breadcrumb.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [CommonModule, RouterModule],
  exports: [BreadcrumbComponent],
  declarations: [BreadcrumbComponent],
})
export class BreadcrumbModule {}
