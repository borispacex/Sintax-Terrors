import { createFeatureSelector, createSelector } from '@ngrx/store';
import { ViewState } from '../states/viewState';

export const selectAppState = createFeatureSelector<ViewState>('appState');

export const selectTexts = createSelector(selectAppState, state => state.texts);
