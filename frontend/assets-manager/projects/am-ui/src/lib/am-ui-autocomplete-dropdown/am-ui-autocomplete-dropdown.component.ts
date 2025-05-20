import {
  AfterViewInit,
  ChangeDetectionStrategy,
  Component,
  Input,
  ViewChild,
  ElementRef,
  forwardRef,
  HostListener,
  ChangeDetectorRef,
  Output,
  EventEmitter,
  ViewEncapsulation,
} from '@angular/core';
import {
  ControlValueAccessor,
  FormControl,
  NG_VALUE_ACCESSOR,
} from '@angular/forms';
import { DropdownItem } from './interfaces/dropdown.model';

@Component({
  selector: 'am-ui-autocomplete-dropdown',
  templateUrl: './am-ui-autocomplete-dropdown.component.html',
  styleUrls: ['./am-ui-autocomplete-dropdown.component.scss'],
  encapsulation: ViewEncapsulation.None,
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => AmUiAutocompleteDropdownComponent),
      multi: true,
    },
  ],
})
export class AmUiAutocompleteDropdownComponent
  implements ControlValueAccessor, AfterViewInit
{
  @Input() label: string = '';
  private _items: DropdownItem[] | null = null;

  @Input()
  set items(val: DropdownItem[] | null) {
    this._items = val;
    this._applyPendingValue();
  }

  get items(): DropdownItem[] | null {
    return this._items;
  }
  @Input() multiple = false;
  @Input() disabled: boolean = false;
  @Input() placeholder = 'Select a item...';
  @Input() control: FormControl | null = null;
  @Input() set focus(isFocused: boolean) {
    this._focus = isFocused;
    if (isFocused) {
      this._focusInput();
    }
  }
  @Output() change = new EventEmitter<any>();
  @Output() onSearch = new EventEmitter<any>();

  // TODO: Take a look at some angular lib to handle this
  @HostListener('document:click', ['$event.target'])
  onClickOutside(target: HTMLElement) {
    if (this.dropdownRoot.nativeElement.contains(target)) {
      return;
    }
    this.opened = false;
  }

  public value: string = '';
  public opened = false;
  public selected: string[] = [];
  private _focus = false;
  private _pendingValue: any = null;

  @ViewChild('inputElement') inputElement!: ElementRef<HTMLInputElement>;
  @ViewChild('dropdownRoot') dropdownRoot!: ElementRef;

  constructor(private _cdr: ChangeDetectorRef) {}

  ngAfterViewInit(): void {
    if (this._focus) {
      this._focusInput();
    }
  }

  public onChange: any = () => {};
  public onTouched: any = () => {};

  get filteredItems(): DropdownItem[] | null {
    if (this.multiple) {
      return this.items!.filter(item =>
        item.label.toLowerCase().includes(this.value.toLowerCase())
      );
    } else {
      return this.items;
    }
  }

  public toggleDropdown(): void {
    if (this.disabled) {
      return;
    }
    this.opened = !this.opened;
  }

  public writeValue(value: any): void {
    // this.selected = this.multiple ? value || [] : value ? [value] : [];
    // this.value = this.multiple ? '' : value;
    this._pendingValue = value;
    this._applyPendingValue();
  }

  public registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  public registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  public onInput(event: Event): void {
    this.opened = true;
    const value = (event.target as HTMLInputElement).value;
    this.value = value;
    if (!this.multiple) {
      if (this.items === null) {
        return;
      }
      const matchingItem = this.items!.find(item => item.label === value);

      if (matchingItem) {
        this.onChange(matchingItem.value);
      } else {
        this.onChange(null);
        this.selected = [];
      }
      this.onSearch.emit(value ?? '');
    } else {
      this.onChange([...this.selected]);
    }
    this.onTouched();
  }

  public selectItem(item: any, event?: MouseEvent): void {
    const isCtrlPressed = event?.ctrlKey;
    if (this.multiple) {
      const index = this.selected.findIndex(i => i === item.value);
      if (index > -1) {
        this.selected.splice(index, 1);
      } else {
        this.selected.push(item.value);
      }
      this.onChange([...this.selected]);
      this.change.emit([...this.selected]);
      this.value = '';
      if (!isCtrlPressed) {
        this.opened = false;
      }
    } else {
      this.selected = [item.value];
      this.onChange(item.value);
      this.change.emit(item.value);
      this.value = item.label;
      this.opened = false;
    }
  }

  public removeItem(value: string): void {
    this.selected = this.selected.filter(v => v !== value);
    this.onChange(this.selected);
  }

  public isSelected(item: DropdownItem): boolean {
    return this.selected.includes(item.value);
  }

  public getLabel(value: any): string {
    const found = this.items!.find(item => item.value === value);
    return found ? found.label : value;
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

  public getFirstError(): string {
    if (!this.control?.errors) {
      return '';
    }

    const errors = this.control.errors;

    if (errors['required']) {
      const isEmpty = this.multiple
        ? this.selected.length === 0
        : !this.selected.length;
      if (isEmpty) {
        return 'This field is required';
      }
    }
    if (errors['invalidSelection']) {
      return 'Please select a valid item from the list';
    }

    if (errors['custom']) {
      return errors['custom'].message || 'Validation error';
    }

    return 'Validation error';
  }

  private _focusInput(): void {
    if (this.inputElement && !this.disabled) {
      setTimeout(() => {
        this.inputElement.nativeElement.focus();
      }, 0);
    }
  }

  private _applyPendingValue(): void {
    if (!this._pendingValue || !this.items) {
      return;
    }
    const value = this._pendingValue;
    this._pendingValue = null;

    if (this.multiple) {
      this.selected = Array.isArray(value) ? value : [];
      this.value = '';
    } else {
      const selectedItem = this.items.find(item => item.value === value);
      this.selected = selectedItem ? [selectedItem.value] : [];
      this.value = selectedItem ? selectedItem.label : '';
    }

    this._cdr.markForCheck();
  }
}
