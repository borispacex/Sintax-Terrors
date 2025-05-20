import { ViewState } from '../states/viewState';
import { createReducer, on } from '@ngrx/store';
import { addText } from '../actions/view-state.actions';

export const initialState: ViewState = {
  texts: [],
};

export const viewStateReducer = createReducer(
  initialState,
  on(addText, (state, { text }) => ({
    ...state,
    texts: [...state.texts, text],
  }))
);
