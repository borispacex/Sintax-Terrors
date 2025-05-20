import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  Input,
  OnDestroy,
  OnInit,
} from '@angular/core';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';
import { FormControl, FormGroup } from '@angular/forms';
import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { AssetFacade } from '../facades/asset.facade';
import { CategoryFacade } from '../facades/category.facade';
import { Router } from '@angular/router';

import {
  Asset,
  CreateAssetRequest,
  UpdateAssetRequest,
} from '../interfaces/asset.interface';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import {
  initialAssetCategory,
  initialAssetStatus,
  initialAssetWorkspace,
  initialCreateAssetForm,
} from '../helpers/initial-asset-form.config';
import { debounceTime, map, switchMap, tap } from 'rxjs/operators';
import { WorkspaceFacade } from '../../workspace/facades/workspace.facade';

@Component({
  selector: 'am-asset-form',
  templateUrl: './asset-form.component.html',
  styleUrls: ['./asset-form.component.scss'],
})
export class AssetFormComponent implements OnInit, OnDestroy, AfterViewInit {
  public category$: Observable<DropdownItem[]> = of([initialAssetCategory]);
  public workspace$: Observable<DropdownItem[]> = of([initialAssetWorkspace]);
  public searchCategory$ = new BehaviorSubject<string>('');
  public searchWorkspace$ = new BehaviorSubject<string>('');
  public assetStatus: DropdownItem[] = initialAssetStatus;

  public assetForm: FormGroup = initialCreateAssetForm;

  @Input() asset: Asset | null = null;

  public loading$: Observable<boolean> = of(false);
  private _desroy$: Subject<void> = new Subject<void>();

  constructor(
    private _modalService: ModalService,
    private _cdr: ChangeDetectorRef,
    private _assetFacade: AssetFacade,
    private _categoryFacade: CategoryFacade,
    private _workspaceFacade: WorkspaceFacade,
    private _router: Router
  ) {}

  public ngOnInit(): void {
    this._initialize();
  }

  public ngAfterViewInit(): void {
    if (this.asset) {
      this.assetForm.patchValue({
        model: this.asset.model,
        serialNumber: this.asset.serialNumber,
        categoryId: this.asset.categoryId,
        purchaseCost: this.asset.purchaseCost,
        status: this.asset.status,
        workspaceId: this.asset.workspaceId,
        purchaseDate: this.asset.purchaseDate,
        notes: this.asset.notes,
      });
    }
  }

  public getControl(name: string): FormControl {
    return this.assetForm.get(name) as FormControl;
  }

  public createAsset(): void {
    if (this.assetForm.invalid) {
      return;
    }

    this.loading$ = this._assetFacade.getIsLoading$(); // true

    this._cdr.markForCheck();

    const request: CreateAssetRequest = this.assetForm.value;
    this._assetFacade.createAsset(request);
  }

  public updateAsset(): void {
    console.log('UPDATE', this.assetForm.value);
    if (this.assetForm.invalid) {
      return;
    }

    this.loading$ = this._assetFacade.getIsLoading$(); // true

    this._cdr.markForCheck();

    const request: CreateAssetRequest = this.assetForm.value;
    this._assetFacade.updateAsset({
      id: this.asset?.id,
      categoryId: request.categoryId,
      serialNumber: request.serialNumber,
      status: request.status,
      model: request.model,
      workspaceId: request.workspaceId,
      purchaseDate: request.purchaseDate,
      purchaseCost: request.purchaseCost,
      notes: request.notes,
    } as UpdateAssetRequest);
  }

  public ngOnDestroy() {
    this._desroy$.next();
    this._desroy$.complete();
  }

  public closeAsset(): void {
    this._modalService.close();
    this.assetForm.reset();
  }

  public searchCategory(name: string): void {
    this.searchCategory$.next(name);
  }

  public searchWorkspace(name: string): void {
    this.searchWorkspace$.next(name);
  }

  public viewHistory(): void {
    if (this.asset?.id) {
      this._modalService.close();
      this._router.navigate(['secure/history', this.asset.id]);
    }
  }

  private _initialize(): void {
    this.assetForm.reset();

    this.category$ = this.searchCategory$.pipe(
      debounceTime(300),
      tap(name => {
        this._categoryFacade.loadCategories({
          page: 1,
          size: 5,
          name: name,
        });
      }),
      switchMap(() => {
        return this._categoryFacade.getCategories$().pipe(
          map(categories => {
            return categories.map(category => ({
              label: category.name,
              value: category.id,
            }));
          })
        );
      })
    );

    this.workspace$ = this.searchWorkspace$.pipe(
      debounceTime(300),
      tap(name => {
        this._workspaceFacade.loadWorkspaces({
          page: 1,
          size: 5,
          name: name,
        });
      }),
      switchMap(() => {
        return this._workspaceFacade.getWorkspaces$().pipe(
          map(workspaces => {
            return workspaces.map(workspace => ({
              label: workspace.city,
              value: workspace.id,
            }));
          })
        );
      })
    );
  }
}
