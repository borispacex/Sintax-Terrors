import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup } from '@angular/forms';

import { CreateTeamRequest } from '../../interfaces/team.interface';
import { TeamFacade } from '../../facades/team.facade';
import { ModalService } from '../../../../modules/shared/modal/services/modal.service';
import { DropdownItem } from '../../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { EmployeeFacade } from '../../../employee/facades/employee.facade';
import { debounceTime, map, switchMap, tap } from 'rxjs/operators';
import {
  initialTeamManager,
  initialIsActiveTeam,
  initialCreateTeamForm,
} from '../../helpers/initial-team-variables.helper';

@Component({
  selector: 'am-create-team',
  templateUrl: './create-team.component.html',
  styleUrls: ['./create-team.component.scss'],
})
export class CreateTeamComponent implements OnInit {
  public teamForm: FormGroup = initialCreateTeamForm;
  public isActiveItems: DropdownItem[] = initialIsActiveTeam;
  public employees$: Observable<DropdownItem[]> = of([initialTeamManager]);
  public search$ = new BehaviorSubject<string>('');

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

  public createTeam(): void {
    const request: CreateTeamRequest = this.teamForm.value;
    this._teamFacade.createTeam(request);
  }

  public searchEmployees(name: string): void {
    this.search$.next(name);
  }

  private _initialize(): void {
    this.teamForm.reset();

    this.employees$ = this.search$.pipe(
      debounceTime(300),
      tap(name => {
        this._employeeFacade.loadEmployees({
          page: 1,
          size: 5,
          search: name,
        });
      }),
      switchMap(() => {
        return this._employeeFacade.getEmployees$().pipe(
          map(employees => {
            return employees.map(employee => ({
              label: `${employee.firstName} ${employee.lastName}`,
              value: employee.id,
            }));
          })
        );
      })
    );
  }
}
