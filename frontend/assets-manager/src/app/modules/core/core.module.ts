import { NgModule } from '@angular/core';
import { StoreStartupModule } from '../../store/startup/store-startup.module';

@NgModule({
  imports: [StoreStartupModule.forRoot()],
  exports: [StoreStartupModule],
})
export class CoreModule {}
