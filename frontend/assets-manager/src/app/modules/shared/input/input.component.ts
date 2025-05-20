import {
  Component,
  Input,
  forwardRef,
  ViewChild,
  ElementRef,
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
} from '@angular/core';
import {
  ControlValueAccessor,
  NG_VALUE_ACCESSOR,
  FormControl,
} from '@angular/forms';

@Component({
  selector: 'am-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => InputComponent),
      multi: true,
    },
  ],
})
export class InputComponent<T = any>
  implements ControlValueAccessor, AfterViewInit
{
  @Input() label: string = '';
  @Input() placeholder: string = '';
  @Input() type: string = 'text';
  @Input() disabled: boolean = false;
  @Input() control: FormControl | null = null;

  @Input() set focus(isFocused: boolean) {
    this._focus = isFocused;
    if (isFocused) {
      this._focusInput();
    }
  }
  private _focus = false;

  @ViewChild('inputElement') inputElement!: ElementRef<HTMLInputElement>;

  value: T | null = null;

  onChange: (value: T) => void = () => {};
  onTouched: () => void = () => {};

  constructor(private _cfd: ChangeDetectorRef) {}

  ngAfterViewInit(): void {
    if (this._focus) {
      this._focusInput();
    }
  }

  private _focusInput(): void {
    if (this.inputElement && !this.disabled) {
      setTimeout(() => {
        this.inputElement.nativeElement.focus();
      }, 0);
    }
  }

  get isRequired(): boolean {
    if (!this.control) {
      return false;
    }
    if (this.control.validator) {
      const validator = this.control.validator(new FormControl());
      return validator && validator['required'];
    }
    return false;
  }

  getFirstError(): string {
    if (!this.control?.errors) {
      return '';
    }

    const errors = this.control.errors;

    if (errors['required']) {
      return 'This field is required';
    }
    if (errors['email']) {
      return 'Invalid email format';
    }
    if (errors['minlength']) {
      return `Minimum ${errors['minlength'].requiredLength} characters`;
    }
    if (errors['maxlength']) {
      return `Maximum ${errors['maxlength'].requiredLength} characters`;
    }
    if (errors['pattern']) {
      return 'Invalid format';
    }
    if (errors['custom']) {
      return errors['custom'].message || 'Validation error';
    }

    return 'Validation error';
  }

  writeValue(value: T): void {
    this.value = value;
    this._cfd.markForCheck();
  }

  registerOnChange(fn: (value: T) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  onInput(event: Event): void {
    const inputValue = (event.target as HTMLInputElement).value;

    this.value = inputValue as unknown as T;
    this.onChange(this.value);
    this.onTouched();
  }
}
