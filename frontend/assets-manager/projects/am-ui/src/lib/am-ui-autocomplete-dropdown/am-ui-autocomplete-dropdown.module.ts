import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AmUiAutocompleteDropdownComponent } from './am-ui-autocomplete-dropdown.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AmUiAutocompleteDropdownComponent],
  imports: [CommonModule, FormsModule],
  exports: [AmUiAutocompleteDropdownComponent],
})
export class AmUiAutocompleteDropdownModule {}
