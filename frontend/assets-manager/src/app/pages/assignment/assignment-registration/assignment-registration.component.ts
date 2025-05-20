import {
  AfterViewInit,
  ChangeDetectionStrategy,
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { CommonPaginationResponse } from '../../../interfaces/common-response/common-pagination-response';
import { CreateAssignmentRequest } from '../interfaces/assignment-employee.interface';
import {
  debounceTime,
  distinctUntilChanged,
  map,
  startWith,
  switchMap,
  tap,
} from 'rxjs/operators';
import { AssignmentFacade } from '../facades/assignment.facade';
import { Asset } from '../../asset/interfaces/asset.interface';
import { Employee } from '../../employee/interfaces/employee.interface';

@Component({
  selector: 'am-assignment-registration',
  templateUrl: './assignment-registration.component.html',
  styleUrls: ['./assignment-registration.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignmentRegistrationComponent
  implements OnInit, AfterViewInit, OnChanges
{
  public employees$: Observable<DropdownItem[]> = of([]);
  public assets$: Observable<DropdownItem[]> = of([]);

  private _assetsAggregatedSubject = new BehaviorSubject<Asset[]>([]);
  public assetsAggregated$ = this._assetsAggregatedSubject.asObservable();
  public assetsByEmployee$: Observable<Asset[]> = of([]);

  public assignmentForm: FormGroup;
  @Input() public employee: Employee | null = null;

  private _searchEmployeeSubject: Subject<string> = new Subject<string>();
  private _searchEmployee$: Observable<string> = this._searchEmployeeSubject
    .asObservable()
    .pipe(debounceTime(500), distinctUntilChanged(), startWith(''));

  private _searchAssetSubject: Subject<string> = new Subject<string>();
  private _searchAsset$: Observable<string> = this._searchAssetSubject
    .asObservable()
    .pipe(debounceTime(500), distinctUntilChanged(), startWith(''));

  private _assetSubject: BehaviorSubject<CommonPaginationResponse<Asset> | null> =
    new BehaviorSubject<CommonPaginationResponse<Asset> | null>(null);
  private _assets$: Observable<CommonPaginationResponse<Asset> | null> =
    this._assetSubject.asObservable();

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

  ngOnChanges(changes: SimpleChanges): void {
    if (this.employee) {
      const employee: AbstractControl | null =
        this.assignmentForm.get('employee');
      employee?.setValue(this.employee.id);
      this._employeeSelectedSubject.next(this.employee.id.toString());
    }
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
    this._assignmentFacade.createAssignment(request);
  }

  public closeModal(): void {
    this._modalService.close();
  }

  public onChangeItem(value: string | string[]): void {
    if (value) {
      this._employeeSelectedSubject.next(value as string);
    }
  }

  public keepAsset(): void {
    const asset: AbstractControl | null = this.assignmentForm.get('asset');
    if (asset) {
      // TODO: Subscription - Refactor to use async pipe or an effect
      this._assets$
        .pipe(
          map(response => {
            if (response === null) {
              return null;
            }
            return response.content.items.find(item => item.id == asset.value);
          }),
          tap(assetItem => {
            if (assetItem) {
              const current = this._assetsAggregatedSubject.getValue();
              const updated = [...current, assetItem];
              this._assetsAggregatedSubject.next(updated);
              asset.setValue('');
            }
          })
        )
        .subscribe();
    }
  }

  public isAssetSelected(): boolean {
    return (
      this.assignmentForm.value.asset !== null &&
      this.assignmentForm.value.asset !== ''
    );
  }

  public isEmployeeSelected(): boolean {
    return (
      this.assignmentForm.value.employee !== null &&
      this.assignmentForm.value.employee !== ''
    );
  }

  public onSearchEmployee(search: string): void {
    this._searchEmployeeSubject.next(search);
  }

  public onSearchAsset(search: string): void {
    this._searchAssetSubject.next(search);
  }

  public deleteAsset(assetId: number): void {
    const current = this._assetsAggregatedSubject.getValue();
    const updated = current.filter(item => item.id !== assetId);
    this._assetsAggregatedSubject.next(updated);
  }

  private _initialize(): void {
    this._loadEmployees();
    this._loadAssets();
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

  private _loadAssets(): void {
    this.assets$ = this._searchAsset$.pipe(
      switchMap(search => {
        return this._assignmentFacade.loadAssets({
          model: search,
          status: 'AVAILABLE',
        });
      }),
      tap(response => this._assetSubject.next({ ...response })),
      map(response => {
        return response.content.items.map(asset => {
          return {
            label: asset.model + ' ' + asset.serialNumber,
            value: asset.id,
          };
        });
      })
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
}
