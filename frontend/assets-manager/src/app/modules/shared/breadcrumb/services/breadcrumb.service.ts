import { Injectable } from '@angular/core';
import {
  ActivatedRoute,
  NavigationEnd,
  Router,
  UrlSegment,
} from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Breadcrumb } from '../interfaces/breadcrumb.interface';
import { filter } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class BreadcrumbService {
  private _breadcrumbsSource: BehaviorSubject<Breadcrumb[]> =
    new BehaviorSubject<Breadcrumb[]>([]);
  breadcrumbs$ = this._breadcrumbsSource.asObservable();

  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {
    // TODO: Subscription - Refactor to avoid use subscriptions
    this._router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        const breadcrumbs: Breadcrumb[] = this._buildBreadcrumbs(
          this._activatedRoute.root
        );
        this._breadcrumbsSource.next(breadcrumbs);
      });
  }

  private _buildBreadcrumbs(
    route: ActivatedRoute,
    url: string = '',
    breadcrumbs: Breadcrumb[] = []
  ): Breadcrumb[] {
    const children: ActivatedRoute[] = route.children;

    for (const child of children) {
      const routeURL: string = child.snapshot.url
        .map((segment: UrlSegment) => segment.path)
        .join('/');
      if (routeURL !== '') {
        url += `/${routeURL}`;
      }

      let label = child.snapshot.data['breadcrumb'];
      if (label) {
        const params = child.snapshot.params;
        Object.keys(params).forEach(key => {
          label = label.replace(`{${key}}`, params[key]);
        });

        breadcrumbs.push({ label, url });
      }

      return this._buildBreadcrumbs(child, url, breadcrumbs);
    }

    return breadcrumbs;
  }
}
