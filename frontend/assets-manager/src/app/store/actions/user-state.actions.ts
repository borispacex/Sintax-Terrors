import { createAction, props } from '@ngrx/store';
import { UserInterface } from '../../modules/shared/profile/interfaces/user.interface';

export const loadUser = createAction('[UserState] Load User');

export const loadUserSuccess = createAction(
  '[UserState] Load User Success',
  props<{ user: UserInterface }>()
);
