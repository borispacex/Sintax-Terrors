import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'am-auth-root',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AuthComponent {}
