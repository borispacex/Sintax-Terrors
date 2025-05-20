import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { DropdownItem } from '../../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { ModalService } from '../../../../modules/shared/modal/services/modal.service';
import { TeamFacade } from '../../../team/facades/team.facade';
import { EmployeeFacade } from '../../facades/employee.facade';
import {
  debounceTime,
  distinctUntilChanged,
  map,
  startWith,
  switchMap,
  tap,
} from 'rxjs/operators';
import {
  initialActiveEmployee,
  initialEmployeeCity,
  initialEmployeeStatus,
  initialEmployeeTeam,
  initialUpdateEmployeeForm,
} from '../../helpers/initial-employee-variables.helper';
import {
  Employee,
  UpdateEmployeeRequest,
} from '../../interfaces/employee.interface';

interface OnInit {}

@Component({
  selector: 'am-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.scss'],
})
export class UpdateEmployeeComponent implements OnInit, OnChanges {
  @Input() activeEmployee: Employee = initialActiveEmployee;

  public employeeForm: FormGroup = initialUpdateEmployeeForm;
  public employeeCity: DropdownItem[] = initialEmployeeCity;
  public employeeStatus: DropdownItem[] = initialEmployeeStatus;
  public teams$: Observable<DropdownItem[]> = of([initialEmployeeTeam]);
  public search$ = new BehaviorSubject<string>('');
  public users$: Observable<DropdownItem[]> = of([]);

  private _searchUserSubject: Subject<string> = new Subject<string>();
  private _searchUser$: Observable<string> = this._searchUserSubject
    .asObservable()
    .pipe(debounceTime(500), distinctUntilChanged(), startWith(''));

  constructor(
    private readonly _modalService: ModalService,
    private readonly _teamFacade: TeamFacade,
    private readonly _employeeFacade: EmployeeFacade,
    private readonly _fb: FormBuilder
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.activeEmployee) {
      const userId: AbstractControl | null = this.employeeForm.get('userId');
      userId?.setValue(this.activeEmployee.userId);

      this.employeeForm.patchValue({
        id: this.activeEmployee.id,
        firstName: this.activeEmployee.firstName,
        middleName: this.activeEmployee.middleName,
        lastName: this.activeEmployee.lastName,
        secondLastName: this.activeEmployee.secondLastName,
        personalEmail: this.activeEmployee.personalEmail,
        workEmail: this.activeEmployee.workEmail,
        city: this.activeEmployee.city,
        status: this.activeEmployee.status,
        ci: this.activeEmployee.ci,
        cellphoneNumber: this.activeEmployee.cellphoneNumber,
        teamId: this.activeEmployee.team?.name,
        userId: this.activeEmployee.userId,
      });
    }
  }

  public closeModal(): void {
    this._modalService.close();
  }

  public updateEmployee(): void {
    const request: UpdateEmployeeRequest = { ...this.employeeForm.value };
    this._employeeFacade.updateEmployee({ ...this.activeEmployee, ...request });
  }

  public searchTeams(teamName: string): void {
    this.search$.next(teamName);
  }

  public searchUsers(name: string): void {
    this._searchUserSubject.next(name);
  }

  public onImageChange(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    this.employeeForm.patchValue({
      image: file,
    });
  }

  private _initialize(): void {
    this.teams$ = this.search$.pipe(
      debounceTime(300),
      tap(name => {
        this._teamFacade.loadTeams({
          page: 1,
          size: 5,
          name: name,
        });
      }),
      switchMap(() => {
        return this._teamFacade.getTeams$().pipe(
          map(teams => {
            return teams.map(team => ({
              label: team.name,
              value: team.id,
            }));
          })
        );
      })
    );

    this.users$ = this._searchUser$.pipe(
      switchMap(search => {
        return this._employeeFacade.loadUsers({ username: search });
      }),
      map(response => {
        return response.content.items.map(user => {
          return {
            label: user.username,
            value: user.id,
          };
        });
      })
    );
  }
}
