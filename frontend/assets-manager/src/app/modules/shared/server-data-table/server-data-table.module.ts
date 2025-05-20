import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServerDataTableComponent } from './server-data-table.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [ServerDataTableComponent],
  imports: [CommonModule, FormsModule],
  exports: [ServerDataTableComponent],
})
export class ServerDataTableModule {}
