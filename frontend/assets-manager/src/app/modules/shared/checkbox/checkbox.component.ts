import { Component, forwardRef, Input, OnDestroy, OnInit } from '@angular/core';
import {
  AbstractControl,
  ControlValueAccessor,
  FormControl,
  NG_VALIDATORS,
  NG_VALUE_ACCESSOR,
  ValidationErrors,
  Validator,
} from '@angular/forms';
import { combineLatest, Observable, of, Subject } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

@Component({
  selector: 'am-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CheckboxComponent),
      multi: true,
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => CheckboxComponent),
      multi: true,
    },
  ],
})
export class CheckboxComponent
  implements ControlValueAccessor, Validator, OnInit, OnDestroy
{
  @Input() label: string = '';
  @Input() control: FormControl | null = null;
  @Input() disabled: boolean = false;

  public value = false;
  public errorMessage$: Observable<string> = of('');

  private _onChange = (_: any) => {};
  private _onTouched = () => {};
  private _destroy$ = new Subject<void>();

  public ngOnInit(): void {
    this._initialize();
  }

  public ngOnDestroy(): void {
    this._destroy$.next();
    this._destroy$.complete();
  }

  public writeValue(val: any): void {
    this.value = val;
  }

  public registerOnChange(fn: any): void {
    this._onChange = fn;
  }

  public registerOnTouched(fn: any): void {
    this._onTouched = fn;
  }

  public setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  public validate(_: AbstractControl): ValidationErrors | null {
    return null;
  }

  public toggleCheckbox(event: Event) {
    if (this.disabled) {
      return;
    }

    this.value = (event.target as HTMLInputElement).checked;
    this._onChange(this.value);
    this._onTouched();
  }

  private _getFirstError(): string {
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

  private _initialize(): void {
    if (this.control) {
      this.errorMessage$ = combineLatest([
        this.control.statusChanges.pipe(startWith(this.control.status)),
        this.control.valueChanges.pipe(startWith(this.control.value)),
      ]).pipe(map(() => this._getFirstError()));
    }
  }
}
