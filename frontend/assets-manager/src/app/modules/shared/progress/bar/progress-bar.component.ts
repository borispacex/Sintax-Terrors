import { ChangeDetectionStrategy, Component, Input } from '@angular/core';

@Component({
  selector: 'am-progress-bar',
  templateUrl: './progress-bar.component.html',
  styleUrls: ['./progress-bar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProgressBarComponent {
  @Input() progress = 0;
  @Input() height = '6px';
  @Input() color = '#2952E1';
  @Input() indeterminate = false;
}
