import { ChangeDetectionStrategy, Component, Input } from '@angular/core';

@Component({
  selector: 'am-progress-circular',
  templateUrl: './progress-circular.component.html',
  styleUrls: ['./progress-circular.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProgressCircularComponent {
  @Input() progress = 0;
  @Input() size = 40;
  @Input() strokeWidth = 4;
  @Input() color = '#2952E1';
  @Input() indeterminate = false;

  get radius(): number {
    return (this.size - this.strokeWidth) / 2;
  }

  get circumference(): number {
    return 2 * Math.PI * this.radius;
  }

  get dashOffset(): number {
    return this.circumference * (1 - this.progress / 100);
  }
}
