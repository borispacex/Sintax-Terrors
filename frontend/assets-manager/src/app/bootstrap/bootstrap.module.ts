import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BootstrapRoutingModule } from './bootstrap-routing.module';
import { BootstrapComponent } from './bootstrap.component';
import { TokenInterceptor } from '../interceptors/token.interceptor';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from '../services/auth.service';
import { CoreModule } from '../modules/core/core.module';
import { ToastModule } from '../modules/shared/toast/toast.module';
import { FormBuilder } from '@angular/forms';

@NgModule({
  declarations: [BootstrapComponent],
  imports: [
    BrowserModule,
    BootstrapRoutingModule,
    CoreModule,
    HttpClientModule,
    ToastModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    CookieService,
    AuthService,
    FormBuilder,
  ],
  bootstrap: [BootstrapComponent],
})
export class BootstrapModule {}
