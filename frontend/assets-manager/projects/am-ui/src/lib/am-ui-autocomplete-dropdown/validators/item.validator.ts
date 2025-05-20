import { DropdownItem } from '../interfaces/dropdown.model';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function ItemValidator(
  items: DropdownItem[],
  fieldIsMultiple: boolean
): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const input = control.value;
    const allowedValues = items.map(i => i.value);

    if (fieldIsMultiple) {
      if (!Array.isArray(input)) {
        return { invalidSelection: true };
      }
      const allValid = input.every(val => allowedValues.includes(val));
      return allValid ? null : { invalidSelection: true };
    } else {
      const isValid = allowedValues.includes(input);
      return isValid ? null : { invalidSelection: true };
    }
  };
}
