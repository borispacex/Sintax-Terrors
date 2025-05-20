import { createReducer, on } from '@ngrx/store';
import { UserState } from '../states/user.state';
import { loadUserSuccess } from '../actions/user-state.actions';

export const initialState: UserState = {
  user: null,
  loading: true,
};

export const userStateReducer = createReducer(
  initialState,
  on(loadUserSuccess, (state, action) => ({
    ...state,
    user: action.user,
    loading: false,
  }))
);
