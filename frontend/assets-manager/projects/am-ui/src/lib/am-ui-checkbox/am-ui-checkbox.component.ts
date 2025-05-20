import {
  Component,
  forwardRef,
  Input,
  OnDestroy,
  OnInit,
  ViewEncapsulation,
} from '@angular/core';
import {
  AbstractControl,
  ControlValueAccessor,
  FormControl,
  NG_VALIDATORS,
  NG_VALUE_ACCESSOR,
  ValidationErrors,
  Validator,
} from '@angular/forms';
import { combineLatest, Subject } from 'rxjs';
import { map, startWith, takeUntil } from 'rxjs/operators';

@Component({
  selector: 'am-ui-checkbox',
  templateUrl: './am-ui-checkbox.component.html',
  styleUrls: ['./am-ui-checkbox.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => AmUiCheckboxComponent),
      multi: true,
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => AmUiCheckboxComponent),
      multi: true,
    },
  ],
})
export class AmUiCheckboxComponent
  implements ControlValueAccessor, Validator, OnInit, OnDestroy
{
  @Input() label: string = '';
  @Input() control: FormControl | null = null;
  @Input() disabled: boolean = false;

  value = false;
  errorMessage: string = '';

  private onChange = (_: any) => {};
  private onTouched = () => {};
  private destroy$ = new Subject<void>();

  ngOnInit(): void {
    if (this.control) {
      combineLatest([
        this.control.statusChanges.pipe(startWith(this.control.status)),
        this.control.valueChanges.pipe(startWith(this.control.value)),
      ])
        .pipe(
          takeUntil(this.destroy$),
          map(() => this.getFirstError())
        )
        .subscribe(error => {
          this.errorMessage = error;
        });
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  writeValue(val: any): void {
    this.value = val;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  validate(_: AbstractControl): ValidationErrors | null {
    return null;
  }

  toggleCheckbox(event: Event) {
    if (this.disabled) {
      return;
    }

    this.value = (event.target as HTMLInputElement).checked;
    this.onChange(this.value);
    this.onTouched();
  }

  private getFirstError(): string {
    if (
      !this.control?.errors ||
      !(this.control.dirty || this.control.touched)
    ) {
      return '';
    }

    const errors = this.control.errors;

    if (errors['requiredTrue'] || errors['required']) {
      return 'This field is required';
    }

    if (errors['custom']) {
      return errors['custom'].message || 'Validation error';
    }

    return 'Validation error';
  }
}
