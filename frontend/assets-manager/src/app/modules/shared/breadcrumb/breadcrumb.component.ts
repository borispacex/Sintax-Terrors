import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Breadcrumb } from './interfaces/breadcrumb.interface';
import { BreadcrumbService } from './services/breadcrumb.service';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'am-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class BreadcrumbComponent implements OnInit {
  public breadcrumbs$: Observable<Breadcrumb[]> = of([]);

  constructor(private _breadcrumbService: BreadcrumbService) {}

  public ngOnInit(): void {
    this._initialize();
  }

  private _initialize(): void {
    this.breadcrumbs$ = this._breadcrumbService.breadcrumbs$;
  }
}
