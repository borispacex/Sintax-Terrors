import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { CreateAssignmentRequest } from '../interfaces/assignment-employee.interface';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import {
  debounceTime,
  distinctUntilChanged,
  map,
  startWith,
  switchMap,
} from 'rxjs/operators';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';
import { AssignmentFacade } from '../facades/assignment.facade';
import { Asset } from '../../asset/interfaces/asset.interface';
import { Employee } from '../../employee/interfaces/employee.interface';

@Component({
  selector: 'am-assignment-delete',
  templateUrl: './assignment-delete.component.html',
  styleUrls: ['./assignment-delete.component.scss'],
})
export class AssignmentDeleteComponent implements OnInit, AfterViewInit {
  public employees$: Observable<DropdownItem[]> = of([]);

  private _assetsAggregatedSubject = new BehaviorSubject<Asset[]>([]);
  public assetsAggregated$ = this._assetsAggregatedSubject.asObservable();
  public assetsByEmployee$: Observable<Asset[]> = of([]);

  public assignmentForm: FormGroup;
  @Input() public employee: Employee | null = null;

  private _searchEmployeeSubject: Subject<string> = new Subject<string>();
  private _searchEmployee$: Observable<string> = this._searchEmployeeSubject
    .asObservable()
    .pipe(debounceTime(500), distinctUntilChanged(), startWith(''));

  private _employeeSelectedSubject: Subject<string> = new Subject<string>();
  private _employeeSelected$: Observable<string> =
    this._employeeSelectedSubject.asObservable();

  constructor(
    private _fb: FormBuilder,
    private _modalService: ModalService,
    private _assignmentFacade: AssignmentFacade
  ) {
    this.assignmentForm = this._fb.group({
      employee: ['', [Validators.required]],
      asset: [''],
      note: ['', [Validators.required]],
      reason: ['', [Validators.required]],
    });
  }

  public ngOnInit(): void {
    this._initialize();
  }

  ngAfterViewInit(): void {
    if (this.employee) {
      const employee: AbstractControl | null =
        this.assignmentForm.get('employee');
      employee?.setValue(this.employee.id);
      this._employeeSelectedSubject.next(this.employee.id.toString());
    }
  }

  public saveAssignment(): void {
    const formValues = this.assignmentForm.value;
    const request: CreateAssignmentRequest = {
      employeeId: formValues.employee,
      reason: formValues.reason,
      note: formValues.note,
      assetIds: this._assetsAggregatedSubject.getValue().map(asset => asset.id),
    };
    this._assignmentFacade.removeAssignment(request);
  }

  public closeModal(): void {
    this._modalService.close();
  }

  public onChangeItem(value: string | string[]): void {
    if (value) {
      this._employeeSelectedSubject.next(value as string);
    }
  }

  public onSearchEmployee(search: string): void {
    this._searchEmployeeSubject.next(search);
  }

  public deleteAsset(asset: Asset): void {
    const current = this._assetsAggregatedSubject.getValue();
    const updated = [...current, asset];
    this._assetsAggregatedSubject.next(updated);
  }

  public restoreAsset(assetId: number): void {
    const current = this._assetsAggregatedSubject.getValue();
    const updated = current.filter(item => item.id !== assetId);
    this._assetsAggregatedSubject.next(updated);
  }

  private _initialize(): void {
    this._loadEmployees();
    this._subscribeAssetByEmployeeSelected();
  }

  private _subscribeAssetByEmployeeSelected(): void {
    this.assetsByEmployee$ = this._employeeSelected$.pipe(
      switchMap(employeeId => {
        return this._assignmentFacade.loadCurrentAssetsByEmployee(employeeId);
      }),
      map(response => response.content)
    );
  }

  private _loadEmployees(): void {
    this.employees$ = this._searchEmployee$.pipe(
      switchMap(search => {
        return this._assignmentFacade.loadEmployees({ search: search });
      }),
      map(response => {
        return response.content.items.map(employee => {
          return {
            label:
              employee.firstName +
              ' ' +
              employee.lastName +
              ' - CI: ' +
              employee.ci,
            value: employee.id,
          };
        });
      })
    );
  }

  public isAssetMarkedForDeletion(asset: any, assetToDelete: any[]): boolean {
    return assetToDelete.some(
      item => item.id === asset.id // use the property that uniquely identifies
    );
  }
}
