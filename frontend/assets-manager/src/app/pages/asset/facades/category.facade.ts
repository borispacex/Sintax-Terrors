import { Store } from '@ngrx/store';
import * as CategorySelectors from '../../../store/selectors/entities/category-state.selectors';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Category } from '../interfaces/category.interface';
import { RequestParams } from '../../../interfaces/common-response/pagination-meta-data.interface';
import * as CategoryActions from '../../../store/actions/entities/category.action';

@Injectable()
export class CategoryFacade {
  constructor(private store: Store) {}

  public getCategories$(): Observable<Category[]> {
    return this.store.select(CategorySelectors.selectEntities);
  }

  public loadCategories(params: RequestParams): void {
    this.store.dispatch(CategoryActions.loadCategories({ params }));
  }
}
