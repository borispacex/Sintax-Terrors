import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SecureRoutingModule } from './routes/secure-routing.module';
import { SecureComponent } from './secure.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { ModalModule } from '../shared/modal/modal.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ProfileModule } from '../shared/profile/profile.module';
import { NotificationModule } from '../shared/notification/notification.module';
import { BreadcrumbModule } from '../shared/breadcrumb/breadcrumb.module';

@NgModule({
  imports: [
    CommonModule,
    SecureRoutingModule,
    ModalModule,
    BreadcrumbModule,
    FontAwesomeModule,
    ProfileModule,
    NotificationModule,
  ],
  declarations: [
    SecureComponent,
    NavbarComponent,
    SidebarComponent,
    MainContentComponent,
  ],
})
export class SecureModule {}
