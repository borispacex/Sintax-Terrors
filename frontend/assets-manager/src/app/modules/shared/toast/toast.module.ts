import { NgModule } from '@angular/core';
import { ToastComponent } from './toast.component';
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [CommonModule, FontAwesomeModule],
  exports: [ToastComponent],
  declarations: [ToastComponent],
})
export class ToastModule {}
