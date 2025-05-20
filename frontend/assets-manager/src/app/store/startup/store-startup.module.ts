import { InjectionToken, ModuleWithProviders, NgModule } from '@angular/core';
import { ActionReducerMap, StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { viewStateReducer } from '../reducers/view-state.reducer';
import { userStateReducer } from '../reducers/user-state.reducer';
import { entityStateReducer } from '../reducers/entity-state.reducer';
import { ENTITY_EFFECTS } from '../effects/effects';

export const ROOT_REDUCERS = new InjectionToken<ActionReducerMap<any>>(
  'Root Reducers',
  {
    factory: () => ({
      viewState: viewStateReducer,
      userState: userStateReducer,
      entityState: entityStateReducer,
    }),
  }
);

export const ROOT_EFFECTS = [...ENTITY_EFFECTS];

export const effectsModuleForRoot = EffectsModule.forRoot(ROOT_EFFECTS);
export const storeModuleForRoot = StoreModule.forRoot(ROOT_REDUCERS, {
  metaReducers: [],
  runtimeChecks: {
    strictStateSerializability: true,
    strictActionSerializability: false,
    strictActionWithinNgZone: true,
    strictActionTypeUniqueness: true,
  },
});
@NgModule({
  imports: [
    storeModuleForRoot,
    effectsModuleForRoot,
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: false }),
  ],
})
export class StoreStartupModule {
  static forRoot(): ModuleWithProviders<StoreStartupModule> {
    return {
      ngModule: StoreStartupModule,
    };
  }
}
