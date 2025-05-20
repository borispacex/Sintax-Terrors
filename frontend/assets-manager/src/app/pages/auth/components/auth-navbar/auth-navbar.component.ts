import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'am-public-navbar',
  templateUrl: './auth-navbar.component.html',
  styleUrls: ['./auth-navbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AuthNavbarComponent {}
