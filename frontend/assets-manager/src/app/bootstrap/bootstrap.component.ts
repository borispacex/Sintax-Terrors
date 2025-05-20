import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'am-app-root',
  templateUrl: './bootstrap.component.html',
  styleUrls: ['./bootstrap.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class BootstrapComponent {
  title = 'assets-manager';
}
