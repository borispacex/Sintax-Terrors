import { createAction, props } from '@ngrx/store';

export const addText = createAction(
  '[AppState] Add Text',
  props<{ text: string }>()
);

export const removeText = createAction(
  '[AppState] Remove Text',
  props<{ index: number }>()
);
