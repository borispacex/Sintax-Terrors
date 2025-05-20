import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
  initialCreateEmployeeForm,
  initialEmployeeCity,
  initialEmployeeStatus,
  initialEmployeeTeam,
} from '../../helpers/initial-employee-variables.helper';
import { CreateEmployeeRequest } from '../../interfaces/employee.interface';

interface OnInit {}

@Component({
  selector: 'am-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.scss'],
})
export class CreateEmployeeComponent implements OnInit {
  public employeeForm: FormGroup = initialCreateEmployeeForm;
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

  public closeModal(): void {
    this._modalService.close();
  }

  public createEmployee(): void {
    const request: CreateEmployeeRequest = this.employeeForm.value;
    this._employeeFacade.createEmployee(request);
  }

  public searchTeams(name: string): void {
    this.search$.next(name);
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
    this.employeeForm.reset();

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
