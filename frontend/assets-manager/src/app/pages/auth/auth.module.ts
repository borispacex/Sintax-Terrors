import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AuthComponent } from './auth.component';
import { AuthRoutingModule } from './routes/auth-routing.module';
import { PublicFooterComponent } from './components/auth-footer/public-footer.component';
import { LoginUserComponent } from './components/login-user/login-user.component';
import { PasswordRecoveryComponent } from './components/password-recovery/password-recovery.component';
import { RecoveryResendComponent } from './components/recovery-resend/recovery-resend.component';
import { PasswordChangeComponent } from './components/password-change/password-change.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CardModule } from '../../modules/shared/card/card.module';
import { AuthNavbarComponent } from './components/auth-navbar/auth-navbar.component';
import { AutocompleteDropdownModule } from '../../modules/shared/autocomplete-dropdown/autocomplete-dropdown.module';
import { AmUiInputModule, AmUiProgressModule } from '@samuelbaz/am-ui';
import { ProgressModule } from '../../modules/shared/progress/progress.module';

@NgModule({
  imports: [
    CommonModule,
    AuthRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    CardModule,
    ProgressModule,
    AutocompleteDropdownModule,
    AmUiInputModule,
    AmUiProgressModule,
  ],
  declarations: [
    AuthComponent,
    PublicFooterComponent,
    AuthNavbarComponent,
    LoginUserComponent,
    PasswordRecoveryComponent,
    RecoveryResendComponent,
    PasswordChangeComponent,
  ],
})
export class AuthModule {}
