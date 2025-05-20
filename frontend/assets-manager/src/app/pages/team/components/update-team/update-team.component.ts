import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';

import { FormBuilder, FormGroup } from '@angular/forms';

import { Team, UpdateTeamRequest } from '../../interfaces/team.interface';
import { TeamFacade } from '../../facades/team.facade';
import { ModalService } from '../../../../modules/shared/modal/services/modal.service';
import { DropdownItem } from '../../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { EmployeeFacade } from '../../../employee/facades/employee.facade';
import { debounceTime, map, switchMap, tap } from 'rxjs/operators';
import {
  initialActiveTeam,
  initialIsActiveTeam,
  initialTeamManager,
  initialUpdateTeamForm,
} from '../../helpers/initial-team-variables.helper';

@Component({
  selector: 'am-update-team',
  templateUrl: './update-team.component.html',
  styleUrls: ['./update-team.component.scss'],
})
export class UpdateTeamComponent implements OnInit, OnChanges {
  @Input() activeTeam: Team = initialActiveTeam;

  public teamForm: FormGroup = initialUpdateTeamForm;
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

  public ngOnChanges(changes: SimpleChanges) {
    const manager = this.activeTeam.projectManager;
    this.teamForm.patchValue({
      id: this.activeTeam.id,
      name: this.activeTeam.name,
      description: this.activeTeam.description,
      isActive: this.activeTeam.isActive ? 'Active' : 'Inactive',
      projectManagerId: `${manager?.firstName} ${manager?.lastName}`,
    });
  }

  public closeModal(): void {
    this._modalService.close();
  }

  public updateTeam(): void {
    const request: UpdateTeamRequest = {
      ...this.teamForm.value,
      isActive: this.teamForm.value.isActive === 'Active',
      projectManagerId: this.activeTeam.projectManager?.id,
    };
    this._teamFacade.updateTeam(request);
  }

  public searchEmployees(name: string): void {
    this.search$.next(name);
  }

  private _initialize(): void {
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
