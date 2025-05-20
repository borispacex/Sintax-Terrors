import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as UserActions from '../../../../store/actions/user-state.actions';
import { Injectable } from '@angular/core';
import {
  selectUser,
  selectIsLoading,
} from '../../../../store/selectors/user-state.selectors';
import { UserInterface } from '../interfaces/user.interface';

@Injectable()
export class UserFacade {
  constructor(private _store: Store) {}

  public getUser$(): Observable<UserInterface | null> {
    return this._store.select(selectUser);
  }

  public getIsLoading$(): Observable<boolean> {
    return this._store.select(selectIsLoading);
  }

  public loadUser(): void {
    this._store.dispatch(UserActions.loadUser());
  }
}
