import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, switchMap } from 'rxjs/operators';
import { Transaction } from '../interfaces/history.interface';
import { Observable, of } from 'rxjs';
import { AssignmentFacade } from '../../assignment/facades/assignment.facade';

@Component({
  selector: 'am-asset-history',
  templateUrl: './asset-history.component.html',
  styleUrls: ['./asset-history.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssetHistoryComponent implements OnInit {
  public assetsHistory$: Observable<Transaction[]> = of([]);
  assetId!: string;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _assignmentFacade: AssignmentFacade
  ) {}

  ngOnInit(): void {
    this._initialize();
  }
  private _initialize(): void {
    this.assetId = this._route.snapshot.paramMap.get('id') ?? '';
    this.assetsHistory$ = this._assignmentFacade
      .loadAssetsHistory(this.assetId)
      .pipe(map(response => response.content));
  }

  goBack(): void {
    this._router.navigate(['/secure/asset']);
  }
}
