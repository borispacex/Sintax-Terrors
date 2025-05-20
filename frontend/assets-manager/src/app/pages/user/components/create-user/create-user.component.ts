import {
  AfterViewInit,
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { Observable, of } from 'rxjs';
import { DropdownItem } from '../../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { map } from 'rxjs/operators';
import { ModalService } from '../../../../modules/shared/modal/services/modal.service';
import { UsersFacade } from '../../facades/users.facade';
import {
  CreateUserRequest,
  UpdateUserRequest,
  User,
} from '../../interfaces/user.interface';

@Component({
  selector: 'am-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss'],
})
export class CreateUserComponent implements OnInit, AfterViewInit, OnChanges {
  public roles$: Observable<DropdownItem[]> = of([]);

  public assignmentForm: FormGroup;
  @Input() public user: User | null = null;

  constructor(
    private _fb: FormBuilder,
    private _modalService: ModalService,
    private _usersFacade: UsersFacade
  ) {
    this.assignmentForm = this._fb.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      role: ['', [Validators.required]],
      state: ['ACTIVE'],
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.user) {
      const role: AbstractControl | null = this.assignmentForm.get('role');
      role?.setValue(this.user.roleId);
      const username: AbstractControl | null =
        this.assignmentForm.get('username');
      username?.setValue(this.user.username);
    }
  }

  public ngOnInit(): void {
    this._initialize();
  }

  ngAfterViewInit(): void {
    if (this.user) {
      const role: AbstractControl | null = this.assignmentForm.get('role');
      role?.setValue(this.user.roleId);
      const username: AbstractControl | null =
        this.assignmentForm.get('username');
      username?.setValue(this.user.username);
    }
  }

  public saveUser(): void {
    const formValues = this.assignmentForm.value;
    const request: CreateUserRequest = {
      username: formValues.username,
      password: formValues.password,
      roleId: formValues.role,
      state: formValues.state,
    };
    this._usersFacade.createUser(request);
  }

  public updateUser(): void {
    const formValues = this.assignmentForm.value;
    const request: UpdateUserRequest = {
      id: this.user?.id!,
      username: formValues.username,
      password: formValues.password,
      roleId: formValues.role,
      state: formValues.state,
    };
    this._usersFacade.updateUser(request);
  }

  public closeModal(): void {
    this._modalService.close();
  }

  private _initialize(): void {
    this._loadRoles();
  }

  private _loadRoles(): void {
    this.roles$ = this._usersFacade.loadRoles({}).pipe(
      map(response => {
        return response.content.items.map(role => {
          return {
            label: role.name,
            value: role.id,
          };
        });
      })
    );
  }
}
