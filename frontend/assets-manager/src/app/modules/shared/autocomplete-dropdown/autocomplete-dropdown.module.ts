import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AutocompleteDropdownComponent } from './autocomplete-dropdown.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AutocompleteDropdownComponent],
  imports: [CommonModule, FormsModule],
  exports: [AutocompleteDropdownComponent],
})
export class AutocompleteDropdownModule {}
