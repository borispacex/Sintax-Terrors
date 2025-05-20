import { ChangeDetectionStrategy, Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'am-public-password-change',
  templateUrl: './password-change.component.html',
  styleUrls: ['./password-change.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PasswordChangeComponent {
  public passwordChangeForm: FormGroup;
  public loading = false;

  constructor(private _fb: FormBuilder) {
    this.passwordChangeForm = this._fb.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      password: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  public getControl(name: string): FormControl {
    return this.passwordChangeForm.get(name) as FormControl;
  }

  public changePassword(): void {
    //TODO Implement password change screen
  }
}
